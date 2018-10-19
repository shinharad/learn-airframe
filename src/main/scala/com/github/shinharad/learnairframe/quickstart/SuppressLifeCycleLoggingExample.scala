package com.github.shinharad.learnairframe.quickstart

import wvlet.airframe._

object SuppressLifeCycleLoggingExample extends App {

  val d = newDesign.noLifeCycleLogging
    .bind[AppConfig].toInstance(AppConfig("Hello Airframe!"))

  d.build[MyApp] { app =>
    println(app.config.appName)
  }

}
