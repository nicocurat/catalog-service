package controllers

import catalog.CatalogServiceGrpc.CatalogService
import catalog.{CatalogIdRequest, None, ProductIdRequest, ProductIdsRequest, ProductsList}
import services.ProductStorageService
import scala.concurrent.Future

object ProductController extends ControllerExecutionContext with CatalogService{

//  override def getProduct(request: ProductIdRequest): Future[Product] = ProductStorageService getProductById request.productId


//  override def getAllProducts: Future[ProductsList] =
  override def getProduct(request: ProductIdRequest): Future[catalog.Product] = ProductStorageService getProductById  (request productId)

  override def getAllProducts(request: None): Future[ProductsList] = (ProductStorageService getAll) map(ProductsList(_))

  override def getData(request: ProductIdsRequest): Future[ProductsList] = (ProductStorageService getData request.productId) map(ProductsList(_))

  override def getProductsFromCatalog(request: CatalogIdRequest): Future[ProductsList] = ???
//    ProductStorageService getProductsFromCatalog (request catalogId) map(ProductsList(_))
}