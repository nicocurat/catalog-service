package controllers

import io.grpc.ManagedChannelBuilder
import org.etcd4s.pb.etcdserverpb.LeaseGrantRequest
import org.etcd4s.{Etcd4sClient, Etcd4sClientConfig}
import product.Status.{Failed, Ok}
import product._
import services.{EtcdService, UserStorageService}
import user.UserServiceGrpc.UserService
import user.{AddItemRequest, AddItemResponse, AddUserResponse, GetAllUsersResponse, GetUserResponse, User, UserId}

import scala.concurrent.Future


class UserController extends ControllerExecutionContext with UserService with GrpcServer{

  //channel to send the gRPC Request
  private lazy val channel = ManagedChannelBuilder.forAddress("localhost", 8081).usePlaintext().build()

  private val stub: ProductServiceGrpc.ProductServiceStub = ProductServiceGrpc.stub(channel)


//  EtcdService.client.kvService.setKey("client-service", "localhost:8081")
//  EtcdService.client.clusterService.




  override def addItem(request: AddItemRequest): Future[AddItemResponse] = {
    UserStorageService insertItem request map(user => {
      AddItemResponse(user)
    })
//    Future.successful(UserId(request user))
  }

  override def addUser(request: User): Future[AddUserResponse] = UserStorageService insert request map{
    case Some(_) => AddUserResponse(Ok)
    case None => AddUserResponse(Failed)
  }

  override def getUser(request: UserId): Future[GetUserResponse] =
    UserStorageService getUserById(request id) map(GetUserResponse(_))



  override def getAllUsers(request: GetAllRequest): Future[GetAllUsersResponse] = UserStorageService.getAll
    .map(users =>
    GetAllUsersResponse(users))


  override def getWishList(request: UserId): Future[ProductIds] = UserStorageService getProducts request.id

  override def getWishListWithDescription(request: UserId): Future[ProductsList] =
  {
    UserStorageService.getProducts(request.id)
      .flatMap(stub.getProducts)
  }

  override def getPing(request: Ping): Future[Pong] = Future.successful(Pong(System.currentTimeMillis().toString))


}
