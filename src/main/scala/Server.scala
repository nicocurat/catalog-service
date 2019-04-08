import catalog.product.CatalogServiceGrpc
import catalog.user.UserServiceGrpc
import io.grpc.{ServerBuilder, ServerServiceDefinition}

import scala.concurrent.ExecutionContext

object Server extends GrpcServer{


  def main(args: Array[String]): Unit = {
    val userSsd: ServerServiceDefinition = UserServiceGrpc.bindService(UserController, ExecutionContext.global)
    val catalogSsd: ServerServiceDefinition = CatalogServiceGrpc.bindService(ProductController, ExecutionContext.global)

  }
}



trait GrpcServer {

  /**
    * Just for demo purposes
    */
  def runServer(ssd: ServerServiceDefinition): Unit = {
    val server = ServerBuilder
      .forPort(50052)
      .addService(ssd)
      .build
      .start

    // make sure our server is stopped when jvm is shut down
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = server.shutdown()
    })

    server.awaitTermination()
  }

}
