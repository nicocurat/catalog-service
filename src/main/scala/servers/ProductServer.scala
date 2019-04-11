package servers

import controllers.{GrpcServer, ProductController}
import product.ProductServiceGrpc

import scala.concurrent.ExecutionContext

object ProductServer extends GrpcServer{

  def main(args: Array[String]): Unit = {
    val ssd = ProductServiceGrpc.bindService(new ProductController(), ExecutionContext.global)
    runServer(ssd, 8080)
  }

}
