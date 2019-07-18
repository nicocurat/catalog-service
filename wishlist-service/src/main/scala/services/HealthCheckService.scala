package services

import healthCheck.HealthCheckGrpc.HealthCheck
import healthCheck.{Ping, Pong}

import scala.concurrent.Future

object HealthCheckService extends ControllerExecutionContext with HealthCheck{
  override def getPing(request: Ping): Future[Pong] = Future(Pong(System.currentTimeMillis()))
}
