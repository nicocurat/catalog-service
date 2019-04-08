import catalog.product
import catalog.product.{CatalogIdRequest, None, ProductIdRequest, ProductsList}
import catalog.product.CatalogServiceGrpc.CatalogService
import controllers.ControllerExecutionContext

import scala.concurrent.Future

object ProductController extends ControllerExecutionContext with CatalogService {

  override def getProduct(request: ProductIdRequest): Future[product.Product] = ProductStorageService getProductById request.productId

  override def getProductsFromCatalog(request: CatalogIdRequest): Future[ProductsList] = ???

  override def getAllProducts(request: None): Future[ProductsList] = (ProductStorageService getAll) map(ProductsList(_))
}