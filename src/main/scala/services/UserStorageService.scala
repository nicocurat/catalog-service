import catalog.user.User
import models.MongoUser
import org.mongodb.scala.bson.collection.immutable.Document
import scala.concurrent.Future

object UserStorageService extends StorageService("user") {

  def getUserById(id: Int): Future[User] = collection
    .find(Document("_id" -> id))
    .first
    .toFuture
    .map(MongoUser from)

}