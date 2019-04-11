package controllers

import io.grpc.ManagedChannelBuilder
import product.{None, Ping, Pong, ProductIds, ProductServiceGrpc, ProductsList}
import services.UserStorageService
import user.UserServiceGrpc.UserService
import user.{AddItemRequest, GetAllUsersResponse, User, UserId}

import scala.concurrent.Future


class UserController extends ControllerExecutionContext with UserService with GrpcServer{

  //channel to send the gRPC Request
  private val channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build()

  //blocking to return the BufferedImage
  private val stub: ProductServiceGrpc.ProductServiceStub = ProductServiceGrpc.stub(channel)



  override def addItem(request: AddItemRequest): Future[User] = {
    UserStorageService insertItem request
//    Future.successful(UserId(request user))
  }

  override def addUser(request: User): Future[UserId] = UserStorageService insert request map(user => UserId(user id))

  override def getUserById(request: UserId): Future[User] = UserStorageService getUserById(request id) flatMap(user => fillProducts(user))

  override def getAllUsers(request: None): Future[GetAllUsersResponse] = UserStorageService.getAll
    .flatMap(users => Future sequence (users map fillProducts))
    .map(users =>
    GetAllUsersResponse(users))


  override def getWishList(request: UserId): Future[ProductIds] = UserStorageService getProducts request.id

  override def getWishListWithDescription(request: UserId): Future[ProductsList] =
  {
    UserStorageService.getProducts(request.id)
      .flatMap(stub.getProducts)
  }

  override def getPing(request: Ping): Future[Pong] = Future.successful(Pong(System.currentTimeMillis().toString))



  private def fillProducts(user: User): Future[User] = {
    stub getProducts  ProductIds(user.products.map(_.id)) map (products =>
      User(user.id, user.name, user.username, products.products))
  }


}
