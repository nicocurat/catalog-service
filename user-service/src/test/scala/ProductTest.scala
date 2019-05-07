import java.util.concurrent.Executors

import io.grpc.ManagedChannelBuilder
import product.Status.Ok
import product.{AddProductResponse, Ping, Product, ProductIdRequest, ProductIds, ProductServiceGrpc}

import scala.concurrent.{ExecutionContext, Future}

class ProductTest extends UnitSpec{

  private val channel = ManagedChannelBuilder.forAddress("localhost", 8081).usePlaintext().build()

  //blocking to return the BufferedImage
  private val stub: ProductServiceGrpc.ProductServiceStub = ProductServiceGrpc.stub(channel)
  implicit val exec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))


  private val pingFut = {
    stub.getPing(Ping())
  }

  private val getProductsFut = {
    stub.getProducts(ProductIds(List(0, 1)))
  }

  private val getAllProductsFur = {
    stub.getAllProducts(product.None())
  }

  private val addUserFut = {
    stub.getProduct(ProductIdRequest(0))
  }

  private val pongFut = {
    stub.getPing(Ping())
  }


  def addProductFut(id: Int): Future[AddProductResponse] = {
    stub.addProduct(Product(10, "cepillo de dientes", "COlgate", 3))
  }


  "ProductService" should "add a new Product" in {
    addProductFut(1).flatMap{
      case AddProductResponse(status) => status match {
        case Ok => stub.getProduct(ProductIdRequest(1)).map {
          _: Product => fail("Should fail")
        }
      }
    }
  }

}

