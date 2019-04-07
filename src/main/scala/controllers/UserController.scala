import catalog.product.None
import catalog.user.{AddItemRequest, GetAllUsersResponse, User, UserId}
import catalog.user.UserServiceGrpc.UserService

import scala.concurrent.Future

object UserController extends UserService {
  override def addItem(request: AddItemRequest): Future[UserId] = ???

  override def addUser(request: User): Future[UserId] = UserStorageService insert request map(user => UserId(user id))

  override def getUserById(request: UserId): Future[User] = UserStorageService getUserById(request id)

  override def getAllUsers(request: None): Future[GetAllUsersResponse] = UserStorageService.getAll.map(users => GetAllUsersResponse(users))
}