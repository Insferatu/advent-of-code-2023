package com.insferatu.aoc2023.task11

import com.insferatu.aoc2023.task11.model.RaceDetails

import scala.Function.tupled
import scala.io.Source
import scala.math._

object Solution {
  def main(args: Array[String]): Unit = {
    val Array(timeSeqStr, distanceSeqStr) = Source.fromFile("input_d6.txt")
      .getLines
      .toArray
    val result = extractTimeSeq(timeSeqStr).zip(extractDistanceSeq(distanceSeqStr))
      .map(tupled(RaceDetails.apply))
      .map(_.calculateWinsCount)
      .product
    println(s"Answer: $result")
  }

  def extractTimeSeq(timeStr: String): Seq[Double] = {
    val timeRegex = "Time(\\s*):(.*)".r
    timeRegex.findFirstMatchIn(timeStr)
      .map(_.group(2).trim.split("\\s+").map(_.toDouble).toSeq)
      .getOrElse(throw new RuntimeException("Incorrect Time input string"))
  }

  def extractDistanceSeq(distanceStr: String): Seq[Double] = {
    val distanceRegex = "Distance(\\s*):(.*)".r
    distanceRegex.findFirstMatchIn(distanceStr)
      .map(_.group(2).trim.split("\\s+").map(_.toDouble).toSeq)
      .getOrElse(throw new RuntimeException("Incorrect Distance input string"))
  }
}
