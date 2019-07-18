package services

import database.WishListStorageService
import io.grpc.ManagedChannelBuilder
import product.{ProductIds, ProductsGrpc, ProductsList}
import status.Status.{FAILED, OK}
import wishlist.WishListGrpc.WishList
import wishlist.{ItemRequest, ItemResponse, UserId}
import utils.EnvironmentProvider
import scala.concurrent.Future


object WishListService extends ControllerExecutionContext with WishList{

  //channel to send the gRPC Request
  private lazy val channel = ManagedChannelBuilder.forAddress(EnvironmentProvider.productServiceHost, EnvironmentProvider.productServicePort.toInt).usePlaintext().build()

  private val stub: ProductsGrpc.ProductsStub = ProductsGrpc.stub(channel)






  override def addItem(request: ItemRequest): Future[ItemResponse] = {
      WishListStorageService insertItem(request.user, request.product) map(bool => {
      if(bool) ItemResponse(OK) else ItemResponse(FAILED)
    })
  }

  override def deleteItem(request: ItemRequest): Future[ItemResponse] = {
    WishListStorageService deleteItem(request.user, request.product) map(bool => {
      if(bool) ItemResponse(OK) else ItemResponse(FAILED)
    })
  }


  override def getWishList(request: UserId): Future[ProductIds] = WishListStorageService getProducts request.id

  override def getWishListWithDescription(request: UserId): Future[ProductsList] =
  {
    WishListStorageService.getProducts(request.id)
      .flatMap(stub.getProducts)
  }



}
