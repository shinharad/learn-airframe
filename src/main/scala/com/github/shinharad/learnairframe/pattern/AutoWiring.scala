package com.github.shinharad.learnairframe.pattern

import wvlet.airframe._

object AutoWiring extends App {

  case class DatabaseConfig(name: String, address: String, port: Int)
  class DBMonitor(db: DatabaseConfig) {}
  trait Database {
    val dbConfig: DatabaseConfig = bind[DatabaseConfig]
    val dbMonitor: DBMonitor = bind { config: DatabaseConfig =>
      new DBMonitor(config)
    }
  }

  trait ConnectionPool {
    val db = bind[Database]
  }

  trait DBClient {
    val connectionPool = bind[ConnectionPool]
  }

  trait HttpClient {
    def post(m: String): Unit = {}
  }

  trait FluentdClient {
    val httpClient = bind[HttpClient]

    def send(message: String): Unit = {
      httpClient.post(message)
    }
  }

  trait A {
    val dbClient = bind[DBClient]
    val flutendClient = bind[FluentdClient]
  }

  trait B {
    val fluentdClient = bind[FluentdClient]
  }

  val d = newDesign
    .bind[DatabaseConfig].toInstance(DatabaseConfig("mydb", "localhost", 5432))
    .bind[FluentdClient].toSingleton

  d.build[A] { a =>
    println(a)
  }

}
