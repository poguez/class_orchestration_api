resolvers += Classpaths.sbtPluginReleases
resolvers += "Flyway" at "https://flywaydb.org/repo"
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.0-M9")
addSbtPlugin("io.spray" %% "sbt-revolver" % "0.8.0")
addSbtPlugin("com.typesafe.sbt" %% "sbt-native-packager" % "1.1.4")
addSbtPlugin("org.flywaydb" % "flyway-sbt" % "4.2.0")
