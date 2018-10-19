package com.github.shinharad.learnairframe.usecases

import wvlet.airframe._

case class Config(host: String, port: Int)

trait Server {
  def launch(host: String, port: Int)
}

class YourServer extends Server {
  def launch(host: String, port: Int) =
    println(s"started $host:$port")
}

trait AppX {

  // bind configurations
  private val config = bind[Config]
  private val server = bind[Server]

  def run {
    server.launch(config.host, config.port)
  }
}

object ConfiguringApplications extends App {

  // Create a new design and add configuration
  val d = newDesign
    .bind[Server].to[YourServer]
    .bind[Config].toInstance(Config("localhost", 8080))

  // Start the application
  d.withSession { session =>
    val app = session.build[AppX]
    app.run
  }

}
