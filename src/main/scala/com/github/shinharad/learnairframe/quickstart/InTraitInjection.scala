package com.github.shinharad.learnairframe.quickstart

import wvlet.airframe._

trait Connection

class ConnectionImpl extends Connection

class Database(val name: String, conn: Connection)

trait DatabaseService {
  val db = bind[Database] // Inject Database as a singleton
}

object InTraitInjection extends App {

  val d = newDesign
    .bind[String].toInstance("MySQL")
    .bind[Connection].to[ConnectionImpl]

  d.build[DatabaseService] { service =>
    println(service.db.name)
  }

}
