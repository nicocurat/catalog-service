import java.util.concurrent.Executors

import catalog.product.Product
import catalog.user.User

import scala.concurrent.ExecutionContext

object Main extends App {

  implicit val exec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(16))

  for {
    first <- ProductStorageService insert Product(0, "Revista", "Para cuando cagas")
    second <- ProductStorageService insert Product(1, "Papel higienico", "Para limpiar la revista")
    third <- ProductStorageService insert Product(2, "Esponja anal", "Para cuando rompes culos")
    user <- UserStorageService insert User(0, "tomas", "piaggio", List(first, second, third))
    last <- UserStorageService getUserById (user id)
  } yield println(third)

//  val before1 = System.currentTimeMillis()
//  UserStorageService.getUserById(0).map(result => {
//    println(s"Fetched user and products in ${System.currentTimeMillis() - before1}")
//    println(s"Result $result")
//  })
//
//  val before2 = System.currentTimeMillis()
//  ProductStorageService.getProductById(0).map(result => {
//    println(s"Fetched products in ${System.currentTimeMillis() - before2}")
//    println(s"Result $result")
//  })

  println("Enter to exit")
  System.in.read
}
