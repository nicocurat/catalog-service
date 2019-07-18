package services

import product.ProductsGrpc.Products
import product._
import database.ProductStorageService
import status.Status.OK

import scala.concurrent.Future

object ProductService extends ControllerExecutionContext with Products {


  override def getProduct(request: ProductIdRequest): Future[ProductResponse] =
    ProductStorageService.getProductById(request.productId).map(product => ProductResponse(product))

  override def getAllProducts(request: GetAllRequest): Future[ProductsList] = (ProductStorageService getAll) map( products => ProductsList(products))

  override def getProducts(request: ProductIds): Future[ProductsList] = (ProductStorageService getData request.productIds) map( products => {
    println(products)
    ProductsList(products)
  })

  override def addProduct(request: Product): Future[AddProductResponse] = ProductStorageService.insert(request).map(_ => AddProductResponse(OK))
}
