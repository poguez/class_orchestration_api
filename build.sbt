name := "ClassOrchestrationAPI"
organization := "me.archdev"
version := "1.0.0"
scalaVersion := "2.12.1"

libraryDependencies ++= {
  val akkaV = "10.0.0"
  val scalaTestV = "3.0.1"
  val slickVersion = "3.2.0"
  val circeV = "0.6.1"
  val slickpg = "0.15.0-RC"

  Seq(
    "com.typesafe.akka" %% "akka-http-core" % akkaV,
    "com.typesafe.akka" %% "akka-http" % akkaV,
    "de.heikoseeberger" %% "akka-http-circe" % "1.15.0",

    "com.typesafe.slick" %% "slick" % slickVersion,
    "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
    "com.github.tminglei" %% "slick-pg" % slickpg,
    "com.github.tminglei" %% "slick-pg_joda-time" % slickpg,
    "com.github.tminglei" %% "slick-pg_circe-json" % slickpg,
    "com.github.tminglei" %% "slick-pg_jts" % slickpg,
    "com.github.tminglei" %% "slick-pg_json4s" % slickpg,
    "com.github.tminglei" %% "slick-pg_play-json" % slickpg,
    "com.github.tminglei" %% "slick-pg_spray-json" % slickpg,
    "com.github.tminglei" %% "slick-pg_argonaut" % slickpg,


    "org.flywaydb" % "flyway-core" % "3.2.1",
    "com.zaxxer" % "HikariCP" % "2.4.5",
    "org.slf4j" % "slf4j-nop" % "1.6.4",

    "io.circe" %% "circe-core" % circeV,
    "io.circe" %% "circe-generic" % circeV,
    "io.circe" %% "circe-parser" % circeV,

    "org.scalatest" %% "scalatest" % scalaTestV % "test",
    "com.typesafe.akka" %% "akka-http-testkit" % akkaV % "test",
    "ru.yandex.qatools.embed" % "postgresql-embedded" % "1.15" % "test"
  )
}

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.191"
)

flywayUrl := "jdbc:h2:file:./target/foobar"

flywayUser := "SA"

Revolver.settings
//enablePlugins(JavaAppPackaging)
//enablePlugins(DockerPlugin)

//dockerExposedPorts := Seq(9000)
//dockerEntrypoint := Seq("bin/%s" format executableScriptName.value, "-Dconfig.resource=docker.conf")