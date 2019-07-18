import java.util.concurrent.Executors

import healthCheck.{HealthCheckGrpc, Ping, Pong}
import io.grpc.{ManagedChannel, ManagedChannelBuilder}
import product._
import status.Status.OK

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future}


class ProductTest extends UnitSpec {

  val channel: ManagedChannel = ManagedChannelBuilder.forAddress("localhost", 8081).usePlaintext().build()

  //blocking to return the BufferedImage
  val stub: ProductsGrpc.ProductsStub = ProductsGrpc.stub(channel)
  val healthStub = HealthCheckGrpc.stub(channel)
  implicit val exec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))




  val getProductsFut: Future[ProductsList] = {
    stub.getProducts(ProductIds(List(0, 1)))
  }

  val getAllProductsFur: Future[ProductsList] = {
    stub.getAllProducts(product.GetAllRequest())
  }

  val addUserFut: Future[ProductResponse] = {
    stub.getProduct(ProductIdRequest(0))
  }

  val pongFut: Future[Pong] = {
    healthStub.getPing(Ping())
  }


  def addProductFut(id: Int): Future[AddProductResponse] = {
    stub.addProduct(Product(id, "Caja de broche", "Colgate", 6))
  }

  var id = 0

  "ProductService" should "add a new Product and get it" in {
//    id+=1
    val status: AddProductResponse = Await.result(addProductFut(id), 400 seconds)
    status.status should be(OK)
    val product: ProductResponse = Await.result(stub.getProduct(ProductIdRequest(id)), 100 millis)
    product.product should equal(Some(Product(id, "Caja de broche", "Colgate", 3)))
  }


  "Product service" should "return None in getting a non-existent Product" in {
    id+=2
    val product: ProductResponse = Await.result(stub.getProduct(ProductIdRequest(id)), 800 seconds)
    product.product should equal(None)
//    product.product should equal(Some(Product(id, "Cepillo de dientes", "Colgate", 3)))
  }


  it should "return all Products" in {
    val productsList = Await.result(stub.getAllProducts(GetAllRequest()), 300 seconds)
//    productsList should not be empty
    productsList.products.size should not be 0
  }


  it should "return several products" in {
    val products: ProductsList = Await.result(stub.getProducts(ProductIds(List(0,4, 6))), 200 seconds)
    println(products.products)
    products.products.length should be(1)
  }

}




