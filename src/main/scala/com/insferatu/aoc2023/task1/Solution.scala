package com.insferatu.aoc2023.task1

import scala.io.Source

object Solution {
  def main(args: Array[String]): Unit = {
    val allCalibrationValues = Source.fromFile("input_d1.txt")
      .getLines
      .toSeq
      .flatMap { line =>
        for {
          first <- line.find(_.isDigit)
          last <- line.findLast(_.isDigit)
        } yield first.asDigit * 10 + last.asDigit
      }
    println(s"Number of found calibration values: ${allCalibrationValues.size}")
    println(s"Total sum: ${allCalibrationValues.sum}")
  }
}
