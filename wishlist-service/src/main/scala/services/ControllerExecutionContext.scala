package services

import java.util.concurrent.Executors

import scala.concurrent.ExecutionContext

class ControllerExecutionContext {

  implicit val exec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))

}
