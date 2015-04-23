import play.PlayJava

name := "sparklefe"

version := "1.0"

lazy val `sparklefe` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaCore,
  javaWs,
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  "org.webjars" % "bootswatch-yeti" % "3.1.1",
  "org.webjars" % "html5shiv" % "3.7.0",
  "org.webjars" % "respond" % "1.4.2")

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")