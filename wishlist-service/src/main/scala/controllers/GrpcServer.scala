package controllers

import io.grpc.{ServerBuilder, ServerServiceDefinition}

trait GrpcServer {


  def runServer(ssd: ServerServiceDefinition, port:Int): Unit = {
    val server = ServerBuilder
      .forPort(port)
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
