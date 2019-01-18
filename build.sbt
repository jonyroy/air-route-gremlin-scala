import Dependencies._
//import com.typesafe.sbt.SbtNativePackager.autoImport.NativePackagerHelper._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "org.roy"
ThisBuild / organizationName := "roy"

lazy val root = (project in file("."))
  .settings(
    name := "air-route-gremlin-scala",

    mainClass in (Compile, run) := Some("org.roy.Main"),

    libraryDependencies += scalaTest % Test,

    libraryDependencies += "org.janusgraph" % "janusgraph-core" % "0.3.1",

    libraryDependencies += "org.janusgraph" % "janusgraph-cql" % "0.3.1",

    libraryDependencies += "com.michaelpollmeier" %% "gremlin-scala" % "3.3.4.1",

    libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25",

    libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.25" % Test,


    libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.25" % Test,



    libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3" % Test


   // mappings in Universal ++= directory("src/main/resources")

    //enablePlugins(JavaAppPackaging)

  )

// Uncomment the following for publishing to Sonatype.
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for more detail.

// ThisBuild / description := "Some descripiton about your project."
// ThisBuild / licenses    := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
// ThisBuild / homepage    := Some(url("https://github.com/example/project"))
// ThisBuild / scmInfo := Some(
//   ScmInfo(
//     url("https://github.com/your-account/your-project"),
//     "scm:git@github.com:your-account/your-project.git"
//   )
// )
// ThisBuild / developers := List(
//   Developer(
//     id    = "Your identifier",
//     name  = "Your Name",
//     email = "your@email",
//     url   = url("http://your.url")
//   )
// )
// ThisBuild / pomIncludeRepository := { _ => false }
// ThisBuild / publishTo := {
//   val nexus = "https://oss.sonatype.org/"
//   if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
//   else Some("releases" at nexus + "service/local/staging/deploy/maven2")
// }
// ThisBuild / publishMavenStyle := true
