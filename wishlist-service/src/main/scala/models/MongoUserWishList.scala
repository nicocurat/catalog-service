package models

import org.mongodb.scala.bson.collection.immutable.Document

object MongoUser extends MongoModel[UserWishList] {

  import scala.collection.JavaConverters._

  override def from(doc: Document): UserWishList = doc.foldLeft(UserWishList(0, Seq.empty)){
    case (acc, ("_id", value)) => acc.copy(id = value.asInt32.getValue)
    case (acc, ("products", value)) => acc.copy(products = value.asArray
      .getValues
      .asScala
      .map(bsonValue => bsonValue.asInt32.getValue))
  }

  override def from(t: UserWishList): Document = Document("_id" -> t.id, "products" -> t.products)
}


case class UserWishList(id: Int, products: Seq[Int])
