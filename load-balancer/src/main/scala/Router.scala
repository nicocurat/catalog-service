import LoadBalancer.{ProductConnection, UserConnection}
import product._


class Router(val users: List[(String, Int)], val products: List[(String, Int)]){

  private var userIndex = 0
  private var productIndex = 0


  private val userConnections = users.map{ case (host: String, port: Int) => new UserConnection(host, port) }

  private val productConnections = products.map{ case (host: String, port: Int) => new ProductConnection(host, port) }

//  new Thread(() => {
//    while(true){
//      ping
//    }
//  })

//  val ex = new ScheduledThreadPoolExecutor(1)
//  val task = new Runnable {
//    def run() = println("Beep!")
//  }
//  val f = ex.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS)
//  f.cancel(false)

  private def nextUser = {
    if (userIndex >= userConnections.length) userIndex = 0
    else userIndex += 1
  }

  private def nextProduct = {
    if (productIndex>= productConnections.length) productIndex = 0
    else productIndex += 1
  }

  def ping: Unit = {
    userConnections.map(user => user.getPing(Ping()))
    productConnections.map(user => user.getPing(Ping()))
    userConnections.filter((connection: UserConnection) => connection.isAlive)
    productConnections.filter((connection: ProductConnection) => connection.isAlive)
    println(userConnections.map(_.isAlive))
    println(productConnections.map(_.isAlive))
  }
}
