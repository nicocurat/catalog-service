package LoadBalancer

import java.io.IOException

import io.grpc.ManagedChannelBuilder
import product._
import product.ProductServiceGrpc.ProductService

import scala.concurrent.{ExecutionContext, Future}


class ProductConnection(val host: String, val port: Int) extends ProductService {

  implicit val ex = ExecutionContext.global

  private val channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()
  private val stub = ProductServiceGrpc.stub(channel)

  private var alive: Boolean = false

  def isAlive = alive

  def toggle = alive = !alive

  override def getProduct(request: ProductIdRequest): Future[ProductResponse] = stub.getProduct(request)

  override def getAllProducts(request: GetAllRequest): Future[ProductsList] = stub.getAllProducts(request)

  override def getProducts(request: ProductIds): Future[ProductsList] = stub.getProducts(request)

  override def getPing(request: Ping): Future[Pong] = {
    try {
      val future: Future[Pong] = stub.getPing(request)
      future.map((p: Pong) => alive = true)
      future
    } catch {
      case e: IOException => {
        alive = false
        Future{Pong("No Response")}
      }
    }
  }

  override def addProduct(request: product.Product): Future[AddProductResponse] = stub.addProduct(request)
}
