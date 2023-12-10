package com.insferatu.aoc2023.task12

import com.insferatu.aoc2023.task11.model.RaceDetails

import scala.io.Source

object Solution {
  def main(args: Array[String]): Unit = {
    val Array(timeStr, distanceStr) = Source.fromFile("input_d6.txt")
      .getLines
      .toArray
    val result = RaceDetails(extractTime(timeStr), extractDistance(distanceStr)).calculateWinsCount
    println(s"Answer: $result")
  }

  def extractTime(timeStr: String): Double = {
    val timeRegex = "Time(\\s*):(.*)".r
    timeRegex.findFirstMatchIn(timeStr)
      .map(_.group(2).trim.split("\\s+").mkString.toDouble)
      .getOrElse(throw new RuntimeException("Incorrect Time input string"))
  }

  def extractDistance(distanceStr: String): Double = {
    val distanceRegex = "Distance(\\s*):(.*)".r
    distanceRegex.findFirstMatchIn(distanceStr)
      .map(_.group(2).trim.split("\\s+").mkString.toDouble)
      .getOrElse(throw new RuntimeException("Incorrect Distance input string"))
  }
}
