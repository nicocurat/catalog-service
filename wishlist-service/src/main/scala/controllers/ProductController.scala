package controllers

import product.ProductServiceGrpc.ProductService
import product.Status.Ok
import product.{AddProductResponse, GetAllRequest, Ping, Pong, Product, ProductIdRequest, ProductIds, ProductResponse, ProductsList}
import services.ProductStorageService

import scala.concurrent.Future

class ProductController extends ControllerExecutionContext with ProductService {


  override def getProduct(request: ProductIdRequest): Future[ProductResponse] =
    ProductStorageService.getProductById(request.productId).map(product => ProductResponse(product))

  override def getAllProducts(request: GetAllRequest): Future[ProductsList] = (ProductStorageService getAll) map( products => ProductsList(products))

  override def getProducts(request: ProductIds): Future[ProductsList] = (ProductStorageService getData request.productIds) map( products => {
    println(products)
    ProductsList(products)
  })

  override def getPing(request: Ping): Future[Pong] = Future.successful(Pong(System.currentTimeMillis.toString))

  override def addProduct(request: Product): Future[AddProductResponse] = ProductStorageService.insert(request).map(_ => AddProductResponse(Ok))
}
