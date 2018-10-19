package com.github.shinharad.learnairframe.quickstart

import wvlet.airframe._

trait MyServerService {
  val service = bind[Server]
    .onInit(_.init)
    .onInject(_.inject)
    .onStart(_.start)
    .beforeShutdown(_.notifyZ)
    .onShutdown(_.stop)
}

trait Server {
  def init    = println("init")
  def inject  = println("inject")
  def start   = println("start")
  def notifyZ = println("notify")
  def stop    = println("stop")
}

object LifeCycleExample extends App {

  val d = newDesign

  d.build[MyServerService] { _ =>
    }

}
