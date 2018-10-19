import Dependencies._

lazy val root = (project in file(".")).
  settings(
    name := "learn-airframe",
    organization := "com.github.shinharad",
    scalaVersion := scalaV,
    version      := "0.1.0-SNAPSHOT",
    scalafmtOnCompile := true,
    libraryDependencies ++= allDependency
  )
