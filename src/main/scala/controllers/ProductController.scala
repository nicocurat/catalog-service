import catalog.product.None
import catalog.user.{AddItemRequest, GetAllUsersResponse, User, UserId}
import catalog.user.UserServiceGrpc.UserService

import scala.concurrent.Future

object ProductController extends UserService {
  override def addItem(request: AddItemRequest): Future[UserId] = ???

  override def addUser(request: User): Future[UserId] = ???

  override def getUserById(request: UserId): Future[User] = ???

  override def getAllUsers(request: None): Future[GetAllUsersResponse] = ???
}