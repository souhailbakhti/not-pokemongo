lazy val commonSettings = Seq(
  organization := "Not Pokémon Go",
  version := "1.0",
  scalaVersion := "2.11.8"

)

lazy val poca = (project in file("."))
    .settings(commonSettings: _*)
    .settings(
      name := "poca",
      assemblyJarName in assembly := "poca-common.jar"
    )
val akkaV = "2.4.10"
lazy val client = (project in file("client"))
    .dependsOn(poca)
    .settings(commonSettings: _*)
    .settings(
      name := "poca-client",
      mainClass in assembly := Some("client.Main"),
      assemblyJarName in assembly := "poca-client.jar",
      libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.11" % "1.0.2",
      libraryDependencies += "org.slick2d" % "slick2d-core" % "1.0.2",
      libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0",
      libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % akkaV,
      libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit" % akkaV,
      libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % akkaV,
      libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % akkaV,
      libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaV,
      libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"


    )

lazy val server = (project in file("server"))

    .dependsOn(poca)
    .settings(commonSettings: _*)
    .settings(
      name := "poca-server",
      mainClass in assembly := Some("server.Main"),
      assemblyJarName in assembly := "poca-server.jar",
      libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaV,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaV,
  "com.typesafe.akka" %% "akka-http-core" % akkaV,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaV,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaV,
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

    )
