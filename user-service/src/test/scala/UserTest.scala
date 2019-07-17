import java.util.concurrent.Executors

import io.grpc.{ManagedChannel, ManagedChannelBuilder}
import product.Status.Ok
import product._
import user._

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext}

class UserTest extends UnitSpec {


  val channel: ManagedChannel = ManagedChannelBuilder.forAddress("172.17.0.10", 8081).usePlaintext().build()

  //blocking to return the BufferedImage
  val stub: UserServiceGrpc.UserServiceStub = UserServiceGrpc.stub(channel)
  implicit val exec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))

  var id = 0

  "UserService" should "add a new User and get it" in {
    id += 1
    val newUser: User = User(id, "Pepe", "Pepe2", Nil)
    val status = Await.result(stub.addUser(newUser), 4 seconds)
    status.status should be(Ok)
    val response: GetUserResponse = Await.result(stub.getUser(UserId(id)), 400 seconds)
    response.user should equal(Some(newUser))
  }


  it should "return None in getting a non-existent User" in {
    id += 2
    val response: GetUserResponse = Await.result(stub.getUser(UserId(id)), 200 seconds)
    println(response)
//    response.user should not equal Some(User(id, "Pepe", "Pepe2", Nil))
    //    product.product should equal(Some(Product(id, "Cepillo de dientes", "Colgate", 3)))
  }

  it should "add a new Item for an existing User" in {
    val newUser: User = User(1, "Pepe", "Pepe2", Nil)
    val response: AddItemResponse = Await.result(stub.addItem(AddItemRequest(3, 2)), 200 seconds)
    response.user should equal(Some(User(1, "Pepe", "Pepe2", List(1))))
  }

  it should "return the wishlist for that user" in {
    val response: ProductsList = Await.result(stub.getWishListWithDescription(UserId(1)), 200 seconds)
    println(response)
    response.products should be(List(Product(1, "Cepillo de dientes", "Colgate", 3)))
  }


  it should "get all Users" in {
    val users = Await.result(stub.getAllUsers(GetAllRequest()), 200 seconds)
    users.users should equal(List(User(1, "Pepe", "Pepe2", List(1))))
  }
  //
  //
  //  it should "return all Products" in {
  //    val productsList = Await.result(stub.getAllProducts(GetAllRequest()), 300 seconds)
  //    //    productsList should not be empty
  //    productsList.products.size should equal(6)
  //  }
  //
  //
  //  it should "return several products" in {
  //    val products: ProductsList = Await.result(stub.getProducts(ProductIds(List(1, 4, 6))), 200 seconds)
  //    println(products.products)
  //    products.products.length should be(3)
  //  }
}
