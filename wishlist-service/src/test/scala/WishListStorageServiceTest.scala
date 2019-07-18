import database.WishListStorageService
import product.{Product, ProductIds}
import TestData._

import scala.concurrent.Await
import scala.concurrent.duration._

class WishListStorageServiceTest extends UnitSpec {




//  "UserService" should "add a new User and get it" in {
//    id = 4
//    val newUser: User = User(id, "Pepe", "Pepe2", Nil)
//    val status = Await.result(stub.addUser(newUser), 400 seconds)
//    status.status should be(Ok)
//    val response: GetUserResponse = Await.result(stub.getUser(UserId(id)), 400 seconds)
//    response.user should equal(Some(newUser))
//  }
//
//
//  it should "return None in getting a non-existent User" in {
//    id += 2
//    val response: GetUserResponse = Await.result(stub.getUser(UserId(id)), 800 seconds)
//    println(response)
////    response.user should not equal Some(User(id, "Pepe", "Pepe2", Nil))
//    //    product.product should equal(Some(Product(id, "Cepillo de dientes", "Colgate", 3)))
//  }
//
//  it should "add a new Item for an existing User" in {
//    val newUser: User = User(1, "Pepe", "Pepe2", Nil)
//    val response: AddItemResponse = Await.result(stub.addItem(AddItemRequest(3, 2)), 200 seconds)
//    response.user should equal(Some(User(1, "Pepe", "Pepe2", List(1))))
//  }
//
//  it should "return the wishlist for that user" in {
//    val response: ProductsList = Await.result(stub.getWishListWithDescription(UserId(1)), 200 seconds)
//    println(response)
//    response.products should be(List(Product(1, "Cepillo de dientes", "Colgate", 3)))
//  }
//
//
//  it should "get all Users" in {
//    val users = Await.result(stub.getAllUsers(GetAllRequest()), 200 seconds)
//    users.users should equal(Vector(User(1, "Pepe", "Pepe2", List(1))))
//  }


  "WishListStorageService" should "create a new wishlist" in {
    val status: Boolean = Await.result(WishListStorageService.insertItem(userId, products.head), 5 seconds)
    status should equal(true)
  }

  "WishListStorageService" should "update a new wishlist" in {
    val status: Boolean = Await.result(WishListStorageService.insertItem(userId, products(2)), 5 seconds)
    status should equal(true)
  }

  "WishListStorageService" should "delete an item" in {
    val status: Boolean = Await.result(WishListStorageService.deleteItem(userId, products(2)), 5 seconds)
    status should equal(true)
  }

  "WishListStorageService" should "delete a wishlist" in {
    val status: Boolean = Await.result(WishListStorageService.deleteItem(userId, products(0)), 5 seconds)
    status should equal(true)
  }


  "WishListStorageService" should "get all products" in {
    val status: ProductIds = Await.result(WishListStorageService.getProducts(userId), 5 seconds)
    status.productIds should equal(Nil)
  }
  //
  //
  //  it should "return all Products" in {
  //    val productsList = Await.result(stub.getAllProducts(GetAllRequest()), 300 seconds)
  //    //    productsList should not be empty
  //    productsList.products.size should equal(6)
  //  }
  //
  //
  //  it should "return several products" in {
  //    val products: ProductsList = Await.result(stub.getProducts(ProductIds(List(1, 4, 6))), 200 seconds)
  //    println(products.products)
  //    products.products.length should be(3)
  //  }
}

object TestData {
  val userId = 0
  val products: List[Int] = List(0, 2, 4)
  val product = Product(0, "Caja de broche", "Colgate", 6)
}
