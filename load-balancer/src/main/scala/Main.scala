
object Main extends App {


  val userList = List(("localhost", 9091))
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
}
