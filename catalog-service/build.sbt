
name := "catalog-service"

version := "0.1"

scalaVersion := "2.12.8"

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

dockerBaseImage := "openjdk:jre-alpine"
enablePlugins(AshScriptPlugin)
dockerExposedPorts in Docker := Seq(8081)

libraryDependencies ++= Seq(
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.6.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test
)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

PB.protoSources in Compile := Seq(baseDirectory.value / "../protos")
