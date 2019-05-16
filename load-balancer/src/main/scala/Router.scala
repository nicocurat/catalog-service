import java.util.concurrent.{ScheduledThreadPoolExecutor, TimeUnit}

import LoadBalancer.{ProductConnection, UserConnection}
import product.ProductServiceGrpc.ProductService
import product._
import user.{AddItemRequest, GetAllUsersResponse, User, UserId}
import user.UserServiceGrpc.UserService

import scala.concurrent.Future


class Router(val users: List[(String, Int)], val products: List[(String, Int)]) extends UserService with ProductService {

  private var userIndex = 0
  private var productIndex = 0


  private val userConnections = users.map{ case (host: String, port: Int) => new UserConnection(host, port) }

  private val productConnections = products.map{ case (host: String, port: Int) => new ProductConnection(host, port) }

  new Thread(() => {
    while(true){
      ping
    }
  })

  val ex = new ScheduledThreadPoolExecutor(1)
  val task = new Runnable {
    def run() = println("Beep!")
  }
  val f = ex.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS)
  f.cancel(false)

  private def nextUser(): Unit = {
    if (userIndex >= userConnections.length) userIndex = 0
    else userIndex += 1
  }

  private def nextProduct(): Unit = {
    if (productIndex>= productConnections.length) productIndex = 0
    else productIndex += 1
  }

  def ping(): Unit = {
    userConnections.map(user => user.getPing(Ping()))
    productConnections.map(user => user.getPing(Ping()))
    userConnections.filter((connection: UserConnection) => connection.isAlive)
    productConnections.filter((connection: ProductConnection) => connection.isAlive)
    println(userConnections.map(_.isAlive))
    println(productConnections.map(_.isAlive))
  }

  def forwardToUser(function: () => Unit): Future[User] = ???

  override def addItem(request: AddItemRequest): Future[User] = userConnections(userIndex).addItem(request)

  override def addUser(request: User): Future[UserId] =  userConnections(userIndex).addUser(request)

  override def getUserById(request: UserId): Future[User] =  userConnections(userIndex).getUserById(request)

  override def getAllUsers(request: None): Future[GetAllUsersResponse] =  userConnections(userIndex).getAllUsers(request)

  override def getWishList(request: UserId): Future[ProductIds] =  userConnections(userIndex).getWishList(request)

  override def getWishListWithDescription(request: UserId): Future[ProductsList] =  userConnections(userIndex).getWishListWithDescription(request)

  override def getProduct(request: ProductIdRequest): Future[Product] = productConnections(productIndex).getProduct(request)

  override def getAllProducts(request: None): Future[ProductsList] = productConnections(productIndex).getAllProducts(request)

  override def getProducts(request: ProductIds): Future[ProductsList] = productConnections(productIndex).getProducts(request)

  override def getPing(request: Ping): Future[Pong] = productConnections(productIndex).getPing(request)

  override def addProduct(request: Product): Future[AddProductResponse] = productConnections(productIndex).addProduct(request)
}
