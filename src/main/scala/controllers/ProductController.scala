package controllers

import product.ProductServiceGrpc.ProductService
import product.Status.Ok
import product.{AddProductResponse, None, Ping, Pong, Product, ProductIdRequest, ProductIds, ProductsList}
import services.ProductStorageService

import scala.concurrent.Future

class ProductController extends ControllerExecutionContext with ProductService {


  override def getProduct(request: ProductIdRequest): Future[Product] = ProductStorageService getProductById  (request productId)

  override def getAllProducts(request: None): Future[ProductsList] = (ProductStorageService getAll) map(ProductsList(_))

  override def getProducts(request: ProductIds): Future[ProductsList] = (ProductStorageService getData request.productIds) map(ProductsList(_))

  override def getPing(request: Ping): Future[Pong] = Future.successful(Pong(System.currentTimeMillis.toString))

  override def addProduct(request: Product): Future[AddProductResponse] = ProductStorageService.insert(request).map(_ => AddProductResponse(Ok))
}
