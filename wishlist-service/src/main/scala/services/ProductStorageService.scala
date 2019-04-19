package services

import models.MongoProduct
import org.bson.types.ObjectId
import org.mongodb.scala.bson.collection.mutable.Document
import org.mongodb.scala.model.Filters
import product.Product

import scala.concurrent.Future

object ProductStorageService extends StorageService("product") {



  def getData(productId: Seq[Int]): Future[Seq[Product]] =
    collection
  .find(Filters.in("_id", productId.toArray:_*))
//    .find(Document("_id" -> "$id" -> productId))
    .map(MongoProduct from)
    .toFuture


  def getProductById(id: Int): Future[Option[Product]] = collection
    .find(Document("_id" -> id))
    .first.toFutureOption()
    .map{
      case Some(doc) => Some(MongoProduct.from(doc))
      case None => None
    }

  def getAll: Future[Seq[Product]] = collection
    .find
    .toFuture
    .map(_ map(MongoProduct from))

  def insert(product: Product): Future[Product] = collection
    .insertOne(MongoProduct from product)
    .toFuture
    .map(_ => product)


}


