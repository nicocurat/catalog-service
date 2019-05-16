import java.io.IOException

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

//  new Thread(() => {
//    while(true){
//      ping
//    }
//  })

//  val ex = new ScheduledThreadPoolExecutor(1)
//  val task = new Runnable {
//    def run() = println("Beep!")
//  }
//  val f = ex.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS)
//  f.cancel(false)

  private def nextUser = {
    if (userIndex >= userConnections.length) userIndex = 0
    else userIndex += 1
  }

  private def nextProduct = {
    if (productIndex>= productConnections.length) productIndex = 0
    else productIndex += 1
  }

  def ping: Unit = {
    userConnections.map(user => user.getPing(Ping()))
    productConnections.map(user => user.getPing(Ping()))
    userConnections.filter((connection: UserConnection) => connection.isAlive)
    productConnections.filter((connection: ProductConnection) => connection.isAlive)
    println(userConnections.map(_.isAlive))
    println(productConnections.map(_.isAlive))
  }

  override def getProduct(request: ProductIdRequest): Future[Product] = {
    try{
      productConnections(productIndex).getPing(Ping())
        .flatMap(_ => productConnections(productIndex).getProduct(request))
    } catch {
      case _: IOException => {
        nextProduct
        getProduct(request)
      }
    }
  }

  override def getAllProducts(request: None): Future[ProductsList] = {
    try {
      productConnections(productIndex).getPing(Ping())
        .flatMap(_ => productConnections(productIndex).getAllProducts(request))
    } catch {
      case _: IOException => {
        nextProduct
        getAllProducts(request)
      }
    }
  }

  override def getProducts(request: ProductIds): Future[ProductsList] = {
    try {
      productConnections(productIndex).getPing(Ping())
        .flatMap(_ => productConnections(productIndex).getProducts(request))
    } catch {
      case _: IOException => {
        nextProduct
        getProducts(request)
      }
    }
  }

  override def addProduct(request: Product): Future[AddProductResponse] = {
    try {
      productConnections(productIndex).getPing(Ping())
        .flatMap(_ => productConnections(productIndex).addProduct(request))
    } catch {
      case _: IOException => {
        nextProduct
        addProduct(request)
      }
    }
  }

  override def addItem(request: AddItemRequest): Future[User] = {
    try {
      userConnections(userIndex).getPing(Ping())
        .flatMap(_ => userConnections(userIndex).addItem(request))
    } catch {
      case _: IOException => {
        nextUser
        addItem(request)
      }
    }
  }

  override def addUser(request: User): Future[UserId] = {
    try {
      userConnections(userIndex).getPing(Ping())
        .flatMap(_ => userConnections(userIndex).addUser(request))
    } catch {
      case _: IOException => {
        nextUser
        addUser(request)
      }
    }
  }

  override def getUserById(request: UserId): Future[User] = {
    try {
      userConnections(userIndex).getPing(Ping())
        .flatMap(_ => userConnections(userIndex).getUserById(request))
    } catch {
      case _: IOException => {
        nextUser
        getUserById(request)
      }
    }
  }

  override def getAllUsers(request: None): Future[GetAllUsersResponse] = {
    try {
      userConnections(userIndex).getPing(Ping())
        .flatMap(_ => userConnections(userIndex).getAllUsers(request))
    } catch {
      case _: IOException => {
        nextUser
        getAllUsers(request)
      }
    }
  }

  override def getWishList(request: UserId): Future[ProductIds] = {
    try {
      userConnections(userIndex).getPing(Ping())
        .flatMap(_ => userConnections(userIndex).getWishList(request))
    } catch {
      case _: IOException => {
        nextUser
        getWishList(request)
      }
    }
  }

  override def getWishListWithDescription(request: UserId): Future[ProductsList] = {
    try {
      userConnections(userIndex).getPing(Ping())
        .flatMap(_ => userConnections(userIndex).getWishListWithDescription(request))
    } catch {
      case _: IOException => {
        nextUser
        getWishListWithDescription(request)
      }
    }
  }

  override def getPing(request: Ping): Future[Pong] = ???
}
