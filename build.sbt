name := "sparklefe"

version := "1.0"

lazy val `sparklefe` = (project in file(".")).enablePlugins(PlayScala).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(jdbc, anorm, cache, ws)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")