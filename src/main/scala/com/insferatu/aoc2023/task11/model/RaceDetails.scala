package com.insferatu.aoc2023.task11.model

import scala.math._

case class RaceDetails(timeLimit: Double, distanceRecord: Double) {
  def calculateWinsCount: Int = {
    val D = timeLimit * timeLimit - 4 * distanceRecord
    val x1 = (timeLimit + sqrt(D)) / 2
    val x2 = (timeLimit - sqrt(D)) / 2
    (ceil(x1) - floor(x2) - 1).toInt
  }
}
