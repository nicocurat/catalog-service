import java.util.concurrent.Executors

import io.grpc.ManagedChannelBuilder
import product.{Ping, Product}
import user.{AddItemRequest, User, UserId, UserServiceGrpc}

import scala.concurrent.ExecutionContext

class GrpcTest extends UnitSpec {

  //channel to send the gRPC Request
  private val channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build()

  //blocking to return the BufferedImage
  private val stub: UserServiceGrpc.UserServiceStub = UserServiceGrpc.stub(channel)
  implicit val exec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))

  "UserServer" should "add a new user" in {
    for {
      first <- stub.getPing(Ping())
      second <- stub.addUser(User(0, "tomas", "piaggio", Nil))
      third <- stub.getUserById(UserId(0))
      fourth <- stub.getAllUsers(product.None())
      last <- stub.addItem(AddItemRequest(2, 0))
    } yield {
      println(first + second.id.toString + third + fourth + last)
    }
  }



  "Hola" should "hola" in {
    fail()
  }

}