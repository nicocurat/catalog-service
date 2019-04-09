package controllers

import catalog.{None, ProductsList}
import services.UserStorageService
import user.{AddItemRequest, GetAllUsersResponse, User, UserId}
import user.UserServiceGrpc.UserService

import scala.concurrent.Future


object UserController extends ControllerExecutionContext with UserService {


  override def addItem(request: AddItemRequest): Future[UserId] = UserStorageService insertItem request map(user => UserId(user id))

  override def addUser(request: User): Future[UserId] = UserStorageService insert request map(user => UserId(user id))

  override def getUserById(request: UserId): Future[User] = UserStorageService getUserById(request id)

  override def getAllUsers(request: None): Future[GetAllUsersResponse] = UserStorageService.getAll.map(users => GetAllUsersResponse(users))

  override def getProducts(request: UserId): Future[ProductsList] = UserStorageService getProducts (request id) map(products => ProductsList(products))
}
