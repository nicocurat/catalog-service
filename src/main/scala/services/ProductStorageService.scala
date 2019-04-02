import catalog.product.Product
import models.MongoProduct
import org.mongodb.scala.bson.collection.mutable.Document

import scala.concurrent.Future

object ProductStorageService extends StorageService("product") {

  def getProductById(id: Int): Future[Product] = collection
    .find(Document("_id" -> id))
    .first
    .toFuture
    .map(MongoProduct from)

  def getAll: Future[Seq[Product]] = collection
    .find
    .toFuture
    .map(_ map(MongoProduct from))

  def insert(product: Product): Future[Product] = collection
    .insertOne(MongoProduct from product)
    .toFuture
    .map(_ => product)
}