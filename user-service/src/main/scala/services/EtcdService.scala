package services

import org.etcd4s.{Etcd4sClient, Etcd4sClientConfig}

import scala.concurrent.ExecutionContext.Implicits.global

object EtcdService {


  // create the client
  val config = Etcd4sClientConfig(
    address = "192.168.0.6",
    port = 2379
  )
  val client = Etcd4sClient.newClient(config)

}
