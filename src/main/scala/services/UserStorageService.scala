package services

import catalog.{CatalogServiceGrpc, ProductIdsRequest, ProductsList}
import catalog.CatalogServiceGrpc.{CatalogServiceBlockingStub, CatalogServiceStub}
import com.mongodb.client.model.ReturnDocument
import io.grpc.ManagedChannelBuilder
import models.MongoUser
import org.mongodb.scala.bson.collection.immutable.Document
import org.mongodb.scala.model.{Filters, FindOneAndUpdateOptions, Updates}
import user.{AddItemRequest, User, UserId}

import scala.concurrent.Future

object UserStorageService extends StorageService("user") {
  //channel to send the gRPC Request
  private val channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build()

  //blocking to return the BufferedImage
  private val stub: CatalogServiceStub = CatalogServiceGrpc.stub(channel)


  def getProducts(userId: Int): Future[Seq[catalog.Product]] = {
    collection.find(Document("_id" -> userId))
      .first
      .toFuture()
      .map(MongoUser from)
      .map(user =>
        user.products
          .map(_.id))
      .map(ids => ProductIdsRequest(ids))
      .map(stub.getData)
      .map(productsList => productsList.map(_.products))
      .flatten
  }


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

  private def fillProducts(user: User): Future[User] = {
    stub getData ProductIdsRequest(user.products.map(_.id)) map (products =>
      User(user.id, user.name, user.username, products.products))
  }
}