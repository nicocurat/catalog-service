package utils

import org.mongodb.scala.Observer
import scala.util.{Failure, Success, Try}

abstract class ObserverWrapper[T] extends Observer[T] {

  override final def onNext(result: T): Unit = apply(Success(result))

  override final def onError(e: Throwable): Unit = apply(Failure(e))

  override final def onComplete(): Unit = ???

  def apply(tryable: Try[T]): Unit
}