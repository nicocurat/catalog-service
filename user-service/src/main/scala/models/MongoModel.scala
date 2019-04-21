package models

import org.mongodb.scala.bson.collection.immutable.Document

trait MongoModel[T] {
  def from(doc: Document): T
  def from(t: T): Document
}
