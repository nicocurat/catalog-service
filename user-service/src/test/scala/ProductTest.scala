import java.util.concurrent.Executors

import io.grpc.ManagedChannelBuilder

import scala.concurrent.ExecutionContext

class ProductTest extends UnitSpec{

//  private val channel = ManagedChannelBuilder.forAddress("localhost", 8081).usePlaintext().build()
//
//  //blocking to return the BufferedImage
//  private val stub: ProductServiceGrpc.ProductServiceStub = ProductServiceGrpc.stub(channel)
//  implicit val exec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))
//
//
//  val pingFut = {
//    stub.getPing(Ping())
//  }
//
//  val getProductsFut = {
//    stub.getProducts(ProductIds(List(0, 1)))
//  }
//
//  val getAllProductsFur = {
//    stub.getAllProducts(product.None())
//  }
//
//  val addUserFut = {
//    stub.getProduct(ProductIdRequest(0))
//  }
//
//  val pongFut = {
//    stub.getPing(Ping())
//  }
//
//
//  def addProductFut(id: Int) = {
//    stub.addProduct(Product(10, "cepillo de dientes", "COlgate", 3))
//  }
//
//
//  "ProductService" should "add a new Product" in {
//    addProductFut(1).flatMap{
//      case AddProductResponse(status) => status match {
//        case Ok => stub.getProduct(ProductIdRequest(1)).map {
//          response: Product => fail("Should fail")
//        }
//      }
//    }
//  }
//
//

}

