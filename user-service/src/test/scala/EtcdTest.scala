import org.etcd4s.pb.etcdserverpb.{LeaseGrantRequest, Member, MemberAddResponse}
import org.etcd4s.{Etcd4sClient, Etcd4sClientConfig}
import services.EtcdService

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class EtcdTest extends UnitSpec {

  // create the client
  val config = Etcd4sClientConfig(
    address = "192.168.0.6",
    port = 2379
  )
  val client = Etcd4sClient.newClient(config)

  // set a key


  "ETCD" should "get the key" in {

    Await.result(client.kvService.setKey("foo", "bar"), 5 seconds)
    // get a key
    Await.result(client.kvService.getKey("foo"), 5 seconds).foreach { result =>
      assert(result.contains("bar"))
      println(result)
    }
    println(Await.result(client.clusterService.memberList(), 3 seconds))

    client.rpcClient.leaseRpc.leaseGrant(LeaseGrantRequest())
  }

}


object Main extends App {


  private val client = EtcdService.client

  val result = Await.result(client.kvService.setKey("client-service", "http://192.168.0.6:9001/"), 10 seconds)
  println(Await.result(client.kvService.getRange("service"), 10 seconds))


}

