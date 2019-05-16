import java.util.concurrent.Executors

import io.grpc.{ManagedChannel, ManagedChannelBuilder}
import product.Status.{Failed, Ok}
import product.{AddProductResponse, GetAllRequest, Ping, Pong, Product, ProductIdRequest, ProductIds, ProductResponse, ProductServiceGrpc, ProductsList}

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.DurationInt


class ProductTest extends UnitSpec {

  val channel: ManagedChannel = ManagedChannelBuilder.forAddress("localhost", 8081).usePlaintext().build()

  //blocking to return the BufferedImage
  val stub: ProductServiceGrpc.ProductServiceStub = ProductServiceGrpc.stub(channel)
  implicit val exec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))


  val pingFut: Future[Pong] = {
    stub.getPing(Ping())
  }

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
    stub.getPing(Ping())
  }


  def addProductFut(id: Int): Future[AddProductResponse] = {
    stub.addProduct(Product(50, "Caja de broche", "Colgate", 6))
  }

  var id = 5

  "ProductService" should "add a new Product and get it" in {
    id+=1
    val status: AddProductResponse = Await.result(addProductFut(id), 400 seconds)
    status.status should be(Ok)
    val product: ProductResponse = Await.result(stub.getProduct(ProductIdRequest(id)), 100 millis)
    product.product should equal(Some(Product(id, "Cepillo de dientes", "Colgate", 3)))
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
    productsList.products.size should equal(6)
  }


  it should "return several products" in {
    val products: ProductsList = Await.result(stub.getProducts(ProductIds(List(1,4, 6))), 200 seconds)
    println(products.products)
    products.products.length should be(1)
  }

}




