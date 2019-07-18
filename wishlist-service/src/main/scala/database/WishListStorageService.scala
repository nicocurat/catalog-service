package database

import com.mongodb.client.model.UpdateOptions
import models.MongoUser
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.model.{Filters, FindOneAndUpdateOptions, ReturnDocument, Updates}
import product.ProductIds

import scala.concurrent.Future

object WishListStorageService extends StorageService("wishList") {


  def getProducts(userId: Int): Future[ProductIds] = {
    collection.find(Document("_id" -> userId))
      .first
      .toFuture()
      .map(MongoUser from)
      .map(user =>
        user.products)
      .map(ids => ProductIds(ids))
//      .flatten
  }


  def insertItem(userId: Int, productId: Int): Future[Boolean] = {
    val options: UpdateOptions = new UpdateOptions().upsert(true)
//    val options: FindOneAndUpdateOptions = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
    collection.updateOne(Filters.equal("_id", userId),
      Updates.addToSet("products", productId), options).toFutureOption().map{
      case Some(_) => true
      case None => false
    }
  }

  def deleteItem(userId: Int, productId: Int): Future[Boolean] = {
    val options: FindOneAndUpdateOptions = new FindOneAndUpdateOptions()
    options.returnDocument(ReturnDocument.AFTER)
    collection.findOneAndUpdate(Filters.equal("_id", userId),
      Updates.pull("products", productId), options).toFutureOption().map{
      case Some(_) => true
      case None => false
    }
  }


}
