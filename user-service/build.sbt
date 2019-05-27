name := "user-service"

version := "0.1"

scalaVersion := "2.12.8"


libraryDependencies ++= Seq(
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.6.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test,
  "com.github.mingchuno" %% "etcd4s-core" % "0.2.0"
)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)
