import catalog.CatalogServiceGrpc
import controllers.{ProductController, UserController}
import io.grpc.{ServerBuilder, ServerServiceDefinition}
import services.GrpcServer
import user.UserServiceGrpc

import scala.concurrent.ExecutionContext

object Server extends GrpcServer {

  def main(args: Array[String]): Unit = {
    val userSsd: ServerServiceDefinition =
      UserServiceGrpc.bindService(UserController, ExecutionContext.global)
    val catalogSsd: ServerServiceDefinition =
      CatalogServiceGrpc.bindService(ProductController, ExecutionContext.global)

  }
}


