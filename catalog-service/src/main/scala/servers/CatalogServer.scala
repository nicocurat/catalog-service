package servers

import healthCheck.HealthCheckGrpc
import io.grpc.ServerBuilder
import product.ProductsGrpc
import services.{HealthCheckService, ProductService}

import scala.concurrent.ExecutionContext

object CatalogServer {

  def main(args: Array[String]): Unit = {
    val ssd = ProductsGrpc.bindService(ProductService, ExecutionContext.global)
    val healthSsd = HealthCheckGrpc.bindService(HealthCheckService, ExecutionContext.global)
    val port = 8081
    val server = ServerBuilder
      .forPort(port)
      .addService(ssd)
      .addService(healthSsd).asInstanceOf[ServerBuilder[_]]
      .build
      .start

    // make sure our server is stopped when jvm is shut down
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = server.shutdown()
    })

    server.awaitTermination()
  }

}
