import catalog.product.CatalogServiceGrpc.CatalogService
import catalog.product._
import catalog.user.{AddItemRequest, AddItemResponse, User}
import catalog.user.UserServiceGrpc.UserService

import scala.concurrent.Future

object Demo extends App {

  val ipad = Product(0, "Ipad", "Apple Ipad 32 Gb")
  val mac = Product(0, "Macbook Pro", "Of course")
  val pixel = Product(0, "Google Pixel", "Better than the iPhone")

  val products = Vector(ipad, mac, pixel)

  val catalog = Catalog(0, "What you need!", products)

  val curi = User(0, "Nicolás", "Curi", products)

  val service = new MockService()

  val catalogService = new ProductCatalogService(catalog)

}

class MockService extends UserService {
  override def addItem(request: AddItemRequest): Future[AddItemResponse] = {
    request.user map { user: User =>
      request.product map { product: Product =>
        user.addProducts(product)
        Future.successful(AddItemResponse(s"Product Added to ${user.username} list"))
      }
    } getOrElse Future.successful(AddItemResponse("Hey there"))
    Future.successful(AddItemResponse(s"Product Added to list"))
  }
}

class ProductCatalogService(catalog: Catalog) extends CatalogService {

  override def getProduct(request: ProductIdRequest): Future[Product] = {
    val product = catalog.products.find(product => product.id == request.productId)
    product map (product => return Future.successful(product))
    Future.successful(null)
  }

  override def getProductsFromCatalog(request: CatalogIdRequest): Future[ProductsList] = {
    Future.successful(ProductsList(catalog.products))
  }

  override def getAllProducts(request: None): Future[ProductsList] = Future.successful(ProductsList(catalog.products))
}
