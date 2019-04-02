import org.mongodb.scala.{MongoClient, MongoDatabase}

import scala.concurrent.ExecutionContext

abstract class StorageService(private val collectionName: String) {
    private lazy val mongoClient: MongoClient = MongoClient()
    protected lazy val database: MongoDatabase = mongoClient.getDatabase("distribuidos-mongo")
    private lazy val collectionObject = database.getCollection(collectionName)
    protected implicit val exec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

    final protected def collection = collectionObject
}