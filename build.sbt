organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test
val googleLogging = "com.google.cloud" % "google-cloud-logging" % "1.14.0"

lazy val `lagom-gcp-demo` = (project in file("."))
  .aggregate(`lagom-gcp-demo-api`, `lagom-gcp-demo-impl`)

lazy val `lagom-gcp-demo-api` = (project in file("lagom-gcp-demo-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `lagom-gcp-demo-impl` = (project in file("lagom-gcp-demo-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest,
      googleLogging
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`lagom-gcp-demo-api`)
