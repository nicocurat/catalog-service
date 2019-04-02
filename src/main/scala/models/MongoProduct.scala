package models
import catalog.product.Product
import org.mongodb.scala.bson.collection.immutable.Document

object MongoProduct extends MongoModel[Product] {

  override def from(doc: Document): Product =  doc.foldLeft(Product(0, "", "")){
    case (acc, ("_id", value)) => acc.copy(id = value.asInt32.getValue)
    case (acc, ("name", value)) => acc.copy(name = value.asString.getValue)
    case (acc, ("description", value)) => acc.copy(description = value.asString.getValue)
  }
}
