package com.insferatu.aoc2023.task2

import scala.io.Source

object Solution {
  private val wordDigitMap = Map(
    "one" -> 1,
    "two" -> 2,
    "three" -> 3,
    "four" -> 4,
    "five" -> 5,
    "six" -> 6,
    "seven" -> 7,
    "eight" -> 8,
    "nine" -> 9,
    "1" -> 1,
    "2" -> 2,
    "3" -> 3,
    "4" -> 4,
    "5" -> 5,
    "6" -> 6,
    "7" -> 7,
    "8" -> 8,
    "9" -> 9,
  )

  def main(args: Array[String]): Unit = {
    val allCalibrationValues = Source.fromFile("input_d1.txt")
      .getLines
      .toSeq
      .flatMap { line =>
        for {
          first <- findFirstDigit(line)
          last <- findLastDigit(line)
        } yield first * 10 + last
      }
    println(s"Number of found calibration values: ${allCalibrationValues.size}")
    println(s"Total sum: ${allCalibrationValues.sum}")
  }

  private def findFirstDigit(line: String): Option[Int] =
    wordDigitMap.keys
      .toSeq
      .map(key => key -> line.indexOf(key))
      .filter(_._2 >= 0)
      .minByOption(_._2)
      .map { case (foundKey, _) =>
        wordDigitMap(foundKey)
      }

  private def findLastDigit(line: String): Option[Int] =
    wordDigitMap.keys
      .toSeq
      .map(key => key -> line.lastIndexOf(key))
      .filter(_._2 >= 0)
      .maxByOption(_._2)
      .map { case (foundKey, _) =>
        wordDigitMap(foundKey)
      }
}
