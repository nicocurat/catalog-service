import catalog.user.{AddItemRequest, User}
import com.mongodb.client.model.ReturnDocument
import models.MongoUser
import org.mongodb.scala.{Completed, Observer}
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.model.{Filters, FindOneAndUpdateOptions, Updates}
import org.mongodb.scala.result.UpdateResult

import scala.concurrent.Future

object UserStorageService extends StorageService("user") {



  def insertItem(request: AddItemRequest): Future[User] = {
    var options: FindOneAndUpdateOptions = new FindOneAndUpdateOptions()
    options.returnDocument(ReturnDocument.AFTER)
    collection.findOneAndUpdate(Filters.equal("id", request.user),
      Updates.addToSet("products", request.product), options).subscribe((doc: Document) =>
      println(doc)

    )
    Future.successful(null)
  }


  def getUserById(id: Int): Future[User] = collection
    .find(Document("_id" -> id))
    .first
    .toFuture
    .map(MongoUser from)
    .map(fillProducts)
    .flatten

  def getAll: Future[Seq[User]] = collection
    .find
    .toFuture
    .map(_ map(MongoUser from))
    .map(users => Future sequence (users map fillProducts))
    .flatten

  def insert(user: User): Future[User] = collection
    .insertOne(MongoUser from user)
    .toFuture
    .map(_ => user)

  private def fillProducts(user: User): Future[User] = Future.sequence(user.products.map(prod => ProductStorageService getProductById(prod id)))
    .map(prods => User(user.id, user.name, user.username, prods))
}