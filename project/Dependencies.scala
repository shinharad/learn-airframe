import sbt._

object Dependencies {
  
  val scalaV = "2.12.7"
  
  val airframeV = "0.69"
  
  def allDependency = airframeDependency ++
    scalaTest
  
  def airframeDependency = Seq(
    "org.wvlet.airframe" %% "airframe" // % "(version)"
  ).map(_ % airframeV)
  
  lazy val scalaTest = Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )
  
}
