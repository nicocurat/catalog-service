import catalog.product.Product

object Main extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  for {
    first <- ProductStorageService.insert(Product(1, "Revista", "Para cuando cagas"))
    second <- ProductStorageService.getProductById(first id)
  } yield println(second)


  Thread.sleep(1000)
}
