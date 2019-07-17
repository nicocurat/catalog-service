
name := "wishlist-service"

version := "0.1"

scalaVersion := "2.12.8"

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

dockerBaseImage := "openjdk:jre-alpine" // Use an image with lower size
// Alpine doesn't come with bash but with ash so we need to enable this plugin to run the project with ash (shell).
enablePlugins(AshScriptPlugin)

mainClass in Compile := Some("servers.ProductServer")

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
