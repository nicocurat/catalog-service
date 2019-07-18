package database

import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}
import utils.EnvironmentProvider

import scala.concurrent.ExecutionContext

abstract class StorageService(private val collectionName: String) {
    private lazy val mongoClient: MongoClient = MongoClient(s"mongodb://${EnvironmentProvider.mongoHost}:${EnvironmentProvider.mongoPort}")
    protected lazy val database: MongoDatabase = mongoClient.getDatabase(s"${EnvironmentProvider.mongoCollection}")
    private lazy val collectionObject = database.getCollection(collectionName)
    protected implicit val exec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

    final protected def collection: MongoCollection[Document] = collectionObject
}
