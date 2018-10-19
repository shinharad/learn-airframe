package com.github.shinharad.learnairframe.quickstart

import wvlet.airframe._

class MyApp(val config: AppConfig)

case class AppConfig(appName: String)

object ConstructorInjectionExample extends App {

  val d = newDesign
    .bind[AppConfig].toInstance(AppConfig("Hello Airframe!"))

  d.build[MyApp] { app =>
    println(app.config.appName)
  }

}
