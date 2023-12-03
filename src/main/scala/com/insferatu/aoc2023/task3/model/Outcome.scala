package com.insferatu.aoc2023.task3.model

case class Outcome(redCount: Int, greenCount: Int, blueCount: Int) {
  def isPossible(bagConfiguration: BagConfiguration): Boolean = {
    redCount <= bagConfiguration.redCount &&
      greenCount <= bagConfiguration.greenCount &&
      blueCount <= bagConfiguration.blueCount
  }
}

object Outcome {
  private val colors = Set("red", "green", "blue")

  def fromString(str: String): Outcome = {
    val countMap = str.split(",")
      .map(_.trim)
      .map { countStr =>
        val Array(numberStr, color) = countStr.split(" ")
        val number = numberStr.toInt
        if (colors.contains(color)) {
          color -> number
        } else {
          throw new RuntimeException("Incorrect color")
        }
      }
      .toMap
    Outcome(
      countMap.get("red").getOrElse(0),
      countMap.get("green").getOrElse(0),
      countMap.get("blue").getOrElse(0)
    )
  }
}
