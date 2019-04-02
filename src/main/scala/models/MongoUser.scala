package models
import catalog.user.User
import org.mongodb.scala.bson.collection.immutable.Document

object MongoUser extends MongoModel[User] {

  override def from(doc: Document): User = doc.foldLeft(User(0, "", "", Seq.empty)){
    case (acc, ("_id", value)) => acc.copy(id = value.asInt32.getValue)
    case (acc, ("name", value)) => acc.copy(name = value.asString.getValue)
    case (acc, ("username", value)) => acc.copy(username = value.asString.getValue)
//    case (acc, ("products", value)) => acc.copy(products = )
  }

  override def from(t: User): Document = Document("_id" -> t.id, "name" -> t.name, "username" -> t.username, "products" -> t.products.map(_ id))
}
