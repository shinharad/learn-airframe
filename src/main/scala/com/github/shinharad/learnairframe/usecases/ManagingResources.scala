package com.github.shinharad.learnairframe.usecases

import wvlet.airframe._

case class DBConfig(url: String, user: String)

trait DBConnection {
  def connect(url: String, user: String)
  def close: Unit
  def query(sql: String)
}

trait DBService {
  private val dbConfig = bind[DBConfig]
  private val connection = bind[DBConnection]
    .onInit(_.connect(dbConfig.url, dbConfig.user))
    .onShutdown(_.close)

  def query(sql: String) = {
    connection.query(sql)
  }
}

trait AppZ {
  val dbService = bind[DBService]

  dbService.query("select * from tbl")
}

object ManagingResources extends App {

  val d = newDesign
    .bind[DBService].toSingleton
    .bind[DBConfig].toInstance(DBConfig("jdbc://...", "user name"))

  d.withSession { session =>
    val app = session.build[AppZ]
  }

}
