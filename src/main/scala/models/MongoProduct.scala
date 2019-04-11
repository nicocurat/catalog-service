package models
import org.mongodb.scala.bson.collection.immutable.Document
import product.Product

object MongoProduct extends MongoModel[Product] {

  override def from(doc: Document): Product =  doc.foldLeft(Product(0, "", "", 0)){
    case (acc, ("_id", value)) => acc.copy(id = value.asInt32.getValue)
    case (acc, ("name", value)) => acc.copy(name = value.asString.getValue)
    case (acc, ("description", value)) => acc.copy(description = value.asString.getValue)
    case (acc, ("price", value)) => acc.copy(price = value.asDouble.getValue)
  }

  override def from(t: Product): Document = Document("_id" -> t.id, "name" -> t.name, "description" -> t.description, "price" -> t.price)
}
