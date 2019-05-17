import product.{AddProductResponse, Product, ProductIdRequest, ProductResponse}
import user.{AddItemRequest, GetUserResponse, User, UserId}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object Main extends App {


  val userList = List(("localhost", 9001))
  val productList = List(("localhost", 8081))
  val router: Router = new Router(userList, productList)

  val interval = 3000
  println("Starting")

  val runnable: Runnable = () => {
    while (true) {
      //      router.ping
      try
        Thread.sleep(interval)
      catch {
        case e: InterruptedException =>
          e.printStackTrace()
      }
    }
  }

  val thread: Thread = new Thread(runnable)
  thread.start()


  val test = router.addUser(User(1, "Nicolás", "nico", Nil)).flatMap {
    addUserResponse => {
      router.getUser(UserId(1)).flatMap {
        user: GetUserResponse => {
          router.addProduct(Product(1, "Cepillo", "Higiene", 3)).flatMap {
            response: AddProductResponse => {
              if (response.status.isOk) router.getProduct(ProductIdRequest(1)) flatMap {
                case product: ProductResponse =>
                  println(product)
                  Future.successful(true)
                case _ =>
                  println("error")
                  Future.successful(false)
              } else Future.successful(false)
            }
          }
        }
      }
    }
  }

  println(Await.result(test, 20 seconds))
}
