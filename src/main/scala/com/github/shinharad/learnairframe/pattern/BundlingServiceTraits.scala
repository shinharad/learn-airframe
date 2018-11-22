package com.github.shinharad.learnairframe.pattern

import wvlet.airframe._
import wvlet.log.LogSupport

object BundlingServiceTraits extends App {

  case class DatabaseConfig(name: String, address: String, port: Int)
  class DBMonitor(db: DatabaseConfig) {}
  trait Database {
    val dbConfig: DatabaseConfig = bind[DatabaseConfig]
    val dbMonitor: DBMonitor = bind { config: DatabaseConfig =>
      new DBMonitor(config)
    }
  }

  case class ConnectionPoolConfig(size: Int = 32)

  trait ConnectionPool {
    val connectionPoolConfig = bind[ConnectionPoolConfig]
    val db = bind[Database]
  }

  trait DBClient {
    val connectionPool = bind[ConnectionPool]
    def query(sql: String): Unit = {}
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
  }

  trait DBClientService {
    val dbClient = bind[DBClient]
  }

  trait FluentdClientService {
    val fluentdClient = bind[FluentdClient]
  }

  // Service mix-in
  trait MyApp extends DBClientService with FluentdClientService {
    dbClient.query("select 1")
    fluentdClient.send("running a query")
  }

  // Just constructor
  class MyApp2(DBClient: DBClient, fluentdClient: FluentdClient)

  d.build[MyApp] { app =>

  }

}
