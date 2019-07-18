import java.util.concurrent.Executors

import io.grpc.{ManagedChannel, ManagedChannelBuilder}
import product.{ProductIds, ProductsList}
import TestData._
import status.Status.OK
import wishlist.{ItemRequest, ItemResponse, UserId, WishListGrpc}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}

class WishListServiceTest extends UnitSpec {

  val channel: ManagedChannel = ManagedChannelBuilder.forAddress("localhost", 9001).usePlaintext().build()

  //blocking to return the BufferedImage
  val stub: WishListGrpc.WishListStub = WishListGrpc.stub(channel)
  implicit val exec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))

  "WishListService" should "add a new Item" in {
    val response: ItemResponse = Await.result(stub.addItem(ItemRequest(userId, products.head)), 10 seconds)
    response.status should equal(OK)
  }

  "WishListService" should "delete a new Item" in {
    val response: ItemResponse = Await.result(stub.deleteItem(ItemRequest(userId, products.head)), 10 seconds)
    response.status should equal(OK)
  }

  "WishListService" should "get all product ids" in {
    val response: ProductIds = Await.result(stub.getWishList(UserId(userId)), 10 seconds)
    response.productIds should equal(Nil)
  }

  "WishListService" should "get all with descriptions" in {
    val response: ProductsList = Await.result(stub.getWishListWithDescription(UserId(userId)), 10 seconds)
    response.products should contain(product)
  }

}
