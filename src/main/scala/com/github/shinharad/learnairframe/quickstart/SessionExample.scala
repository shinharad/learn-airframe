package com.github.shinharad.learnairframe.quickstart

import wvlet.airframe._

object SessionExample extends App {

  val session = newDesign.newSession
  try {
    session.start

  } finally {
    session.shutdown
  }

}
