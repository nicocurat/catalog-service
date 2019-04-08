import catalog.product.CatalogServiceGrpc.CatalogService
import catalog.product._
import catalog.user.{AddItemRequest, GetAllUsersResponse, User, UserId}
import catalog.user.UserServiceGrpc.UserService

import scala.collection.immutable
import scala.concurrent.Future

object Demo {

  val ipad = Product(0, "Ipad", "Apple Ipad 32 Gb")
  val mac = Product(0, "Macbook Pro", "Of course")
  val pixel = Product(0, "Google Pixel", "Better than the iPhone")

  val products: immutable.Seq[Product] = Vector(ipad, mac, pixel)

  val catalog = Catalog(0, "What you need!", products)

  val curi = User(0, "Nicolás", "Curi", products)

  val service = new MockService()

  val catalogService = new ProductCatalogService(catalog)

}

class MockService extends UserService {
  override def addUser(request: User): Future[UserId] = ???

  override def getUserById(request: UserId): Future[User] = ???

  override def getAllUsers(request: None): Future[GetAllUsersResponse] = ???

  override def addItem(request: AddItemRequest): Future[UserId] = ???
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
