package servers

import controllers.{GrpcServer, UserController}
import user.UserServiceGrpc

import scala.concurrent.ExecutionContext

object UserServer extends GrpcServer{

  def main(args: Array[String]): Unit = {
    val ssd = UserServiceGrpc.bindService(new UserController(), ExecutionContext.global)
    runServer(ssd, 9001)
  }


}
