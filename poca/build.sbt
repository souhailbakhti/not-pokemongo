lazy val commonSettings = Seq(
  organization := "Not Pokémon Go",
  version := "1.0",
  scalaVersion := "2.11.8",
  libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.11" % "1.0.2"
)

lazy val poca = (project in file("."))
    .settings(commonSettings: _*)
    .settings(
      name := "poca",
      assemblyJarName in assembly := "poca-common.jar"
    )

lazy val client = (project in file("client"))
    .dependsOn(poca)
    .settings(commonSettings: _*)
    .settings(
      name := "poca-client",
      mainClass in assembly := Some("client.Main"),
      assemblyJarName in assembly := "poca-client.jar"
    )

lazy val server = (project in file("server"))
    .dependsOn(poca)
    .settings(commonSettings: _*)
    .settings(
      name := "poca-server",
      mainClass in assembly := Some("server.Main"),
      assemblyJarName in assembly := "poca-server.jar"
    )
