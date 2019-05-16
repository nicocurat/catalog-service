package services

import models.MongoUser
import org.mongodb.scala.model.{Filters, FindOneAndUpdateOptions, ReturnDocument, Updates}
import product.ProductIds
import user.{AddItemRequest, User}
import org.mongodb.scala.bson.collection.immutable.Document

import scala.concurrent.Future

object UserStorageService extends StorageService("user") {


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


  def insertItem(request: AddItemRequest): Future[Option[User]] = {
    val options: FindOneAndUpdateOptions = new FindOneAndUpdateOptions()
    options.returnDocument(ReturnDocument.AFTER)
    collection.findOneAndUpdate(Filters.equal("_id", request.user),
      Updates.addToSet("products", request.product), options).toFutureOption().map{
      case Some(user) => Some(MongoUser from user)
    }
  }

  def getUserById(id: Int): Future[Option[User]] = collection
    .find(Document("_id" -> id))
    .first
    .toFutureOption()
    .map{
      case Some(user) => Some(MongoUser from user)
    }
//    .map(fillProducts)
//    .flatten

  def getAll: Future[Seq[User]] = collection
    .find
    .toFuture
    .map(_ map(MongoUser from))
//    .flatMap(users => Future sequence (users map fillProducts))

  def insert(user: User): Future[Option[User]] = collection
    .insertOne(MongoUser from user)
    .toFuture
    .map(_ => Some(user)).recover{
    case exception: Exception =>
      exception.printStackTrace()
      None
  }


}