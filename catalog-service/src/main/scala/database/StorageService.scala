package database

import com.mongodb.{MongoClientSettings, MongoDriverInformation}
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

import scala.concurrent.ExecutionContext

abstract class StorageService(private val collectionName: String) {
    private lazy val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")
    protected lazy val database: MongoDatabase = mongoClient.getDatabase("catalogDB")
    private lazy val collectionObject = database.getCollection(collectionName)
    protected implicit val exec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

    final protected def collection: MongoCollection[Document] = collectionObject
}
