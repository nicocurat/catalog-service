import catalog.product.Product
import models.MongoProduct
import org.mongodb.scala.bson.collection.immutable.Document
import scala.concurrent.Future

object ProductStorageService extends StorageService("product") {

  def getProductById(id: Int): Future[Product] = collection
    .find(Document("_id" -> id))
    .first
    .toFuture
    .map(MongoProduct from)

}