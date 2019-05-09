package LoadBalancer

import java.io.IOException

import io.grpc.ManagedChannelBuilder
import product._
import user._
import user.UserServiceGrpc.UserService

import scala.concurrent.{ExecutionContext, Future}

class UserConnection(val host: String, val port: Int) extends UserService {

  implicit val ex = ExecutionContext.global

  private val channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()
  private val stub = UserServiceGrpc.stub(channel)

  private var alive: Boolean = false

  def isAlive = alive

  def toggle = alive = !alive

  override def addItem(request: AddItemRequest): Future[User] = stub.addItem(request)

  override def addUser(request: User): Future[UserId] = stub.addUser(request)

  override def getUserById(request: UserId): Future[User] = stub.getUserById(request)

  override def getAllUsers(request: None): Future[GetAllUsersResponse] = stub.getAllUsers(request)

  override def getWishList(request: UserId): Future[ProductIds] = stub.getWishList(request)

  override def getWishListWithDescription(request: UserId): Future[ProductsList] = stub.getWishListWithDescription(request)

  override def getPing(request: Ping): Future[Pong] = {
    try {
      val future: Future[Pong] = stub.getPing(request)
      future.map((p: Pong) => alive = true)
      future
    } catch {
      case e: IOException => {
        alive = false
        Future{Pong("No Response")}
      }
    }
  }
}
