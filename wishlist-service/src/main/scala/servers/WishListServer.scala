package servers

import healthCheck.HealthCheckGrpc
import io.grpc.ServerBuilder
import services.{HealthCheckService, WishListService}
import wishlist.WishListGrpc

import scala.concurrent.ExecutionContext

object WishListServer{

  def main(args: Array[String]): Unit = {
    val ssd = WishListGrpc.bindService(WishListService, ExecutionContext.global)
    val healthSsd = HealthCheckGrpc.bindService(HealthCheckService, ExecutionContext.global)

    val port = 9001
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
