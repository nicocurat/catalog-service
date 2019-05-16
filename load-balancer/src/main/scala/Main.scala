
object Main extends App {


  val userList = List(("localhost", 1000), ("127.0.0.1", 2000), ("127.30.30.23", 3000))
  val productList = List(("localhost", 4000), ("127.0.0.1", 5000), ("127.30.30.23", 6000))
  val router: Router = new Router(userList, productList)

  val interval = 3000
  println("Empezando")

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
}
