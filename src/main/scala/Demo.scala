
import java.util.concurrent.Executors

import io.grpc.ManagedChannelBuilder
import product.{Ping, Product, ProductIdRequest, ProductIds, ProductServiceGrpc}
import user.{AddItemRequest, GetAllUsersResponse, User, UserId, UserServiceGrpc}

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext}


object UserDemo extends App {
  //channel to send the gRPC Request
  private val channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build()

  //blocking to return the BufferedImage
  private val stub: UserServiceGrpc.UserServiceStub = UserServiceGrpc.stub(channel)
  implicit val exec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))


  val addItemFut = {
    stub.addItem(AddItemRequest(0, 10))
  }

  val getAllUsersFut = {
    stub.getAllUsers(product.None())
  }

  val getUserByIdFut = {
    stub.getUserById(UserId(0))
  }

  val addUserFut = {
    stub.addUser(User(0, "tomas", "piaggio", Nil))
  }

  val pongFut = {
    stub.getPing(Ping())
  }

  val getWishList = {
    stub.getWishListWithDescription(UserId(0))
  }

  //  for {
  //  first <- pongFut
  //    second <- addUserFut
  //    third <- getUserByIdFut
  //    fourth <- getAllUsersFut
  //    last <- addItemFut
  //  } yield {
  //    println(first + second.id.toString + third + fourth + last)
  //  }


  //  val pong = Await.result(pongFut, 10000 millis)
    val user: User = Await.result(addItemFut, 10000 millis)
//  val wishList = Await.result(getWishList, 10000 seconds)
//  val getAll: GetAllUsersResponse = Await.result(getAllUsersFut, 10000 seconds)
  //  val getById: User = Await.result(getUserByIdFut, 10000 millis)
  //  println(pong)
    println(user)
//  println(wishList)
  //  println(getById)

}

//
//  val ipad = Product(0, "Ipad", "Apple Ipad 32 Gb")
//  val mac = Product(0, "Macbook Pro", "Of course")
//  val pixel = Product(0, "Google Pixel", "Better than the iPhone")
//
//  val products: immutable.Seq[Product] = Vector(ipad, mac, pixel)
//
//  val catalog = Catalog(0, "What you need!", products)
//
//  val curi = User(0, "Nicolás", "Curi", products)
//
//  val service = new MockService()
//
//  val catalogService = new ProductCatalogService(catalog)
//
//}
//
//}
//
//class ProductCatalogService(catalog: Catalog) extends CatalogService {
//
//  override def getProduct(request: ProductIdRequest): Future[Product] = {
//    val product = catalog.products.find(product => product.id == request.productId)
//    product map (product => return Future.successful(product))
//    Future.successful(null)
//  }
//
//  override def getProductsFromCatalog(request: CatalogIdRequest): Future[ProductsList] = {
//    Future.successful(ProductsList(catalog.products))
//  }
//
//  override def getAllProducts(request: None): Future[ProductsList] = Future.successful(ProductsList(catalog.products))
//
//  //TODO
//  override def getData(request: ProductIdsRequest): Future[ProductsList] = ???
//}


object ProductDemo extends App {
  //channel to send the gRPC Request
  private val channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build()

  //blocking to return the BufferedImage
  private val stub: ProductServiceGrpc.ProductServiceStub = ProductServiceGrpc.stub(channel)
  implicit val exec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))


  val pingFut = {
    stub.getPing(Ping())
  }

  val getProductsFut = {
    stub.getProducts(ProductIds(List(0, 1)))
  }

  val getAllProductsFur = {
    stub.getAllProducts(product.None())
  }

  val addUserFut = {
    stub.getProduct(ProductIdRequest(0))
  }

  val pongFut = {
    stub.getPing(Ping())
  }

  val addProductFut = {
    stub.addProduct(Product(10, "cepillo de dientes", "COlgate", 3))
  }


  val status = Await.result(addProductFut, 10000 millis)
  println(status)

}
