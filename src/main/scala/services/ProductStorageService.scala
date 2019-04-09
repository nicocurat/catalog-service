package services

import catalog.{CatalogServiceGrpc, Product, ProductIdsRequest}
import controllers.ProductController
import io.grpc.{ServerBuilder, ServerServiceDefinition}
import models.MongoProduct
import org.mongodb.scala.MongoCollection
import org.mongodb.scala.bson.collection.mutable.Document

import scala.concurrent.Future
import org.mongodb.scala

object ProductStorageService extends StorageService("product") with GrpcServer {
  def getProductsFromCatalog(i: Int) =
//    collection
//    .find(Document("_catalogId"))
  null


  def getData(productId: Seq[Int]): Future[Seq[Product]] = collection
    .find(Document("_id" -> productId))
    .map(MongoProduct from)
    .toFuture


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


  def main(args: Array[String]): Unit = {
  val ssd = CatalogServiceGrpc.bindService(ProductController, ProductController.exec)
  runServer(ssd)
}
}


trait GrpcServer {


  def runServer(ssd: ServerServiceDefinition): Unit = {
    val server = ServerBuilder
      .forPort(8080)
      .addService(ssd)
      .build
      .start

    // make sure our server is stopped when jvm is shut down
    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = server.shutdown()
    })

    server.awaitTermination()
  }

}