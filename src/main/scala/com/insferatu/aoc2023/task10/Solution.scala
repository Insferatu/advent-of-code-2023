package com.insferatu.aoc2023.task10

import com.insferatu.aoc2023.task9.model.Converter

import scala.collection.immutable.NumericRange
import scala.io.Source

object Solution {
  def main(args: Array[String]): Unit = {
    val seedsStr +: converterStrSeq = Source.fromFile("input_d5.txt")
      .getLines
      .mkString("\n")
      .split("\n\n")
      .toSeq: @unchecked
    val seedRangeSeq = extractSeedRangeSeq(seedsStr)
    val converterSeq = converterStrSeq.map(Converter.fromString)
    val result = converterSeq
      .foldLeft(seedRangeSeq) { case (input, converter) => converter.convertRangeSeq(input) }
      .head
      .start
    println(s"Answer: $result")
  }

  def extractSeedRangeSeq(seedsStr: String): Seq[NumericRange[Long]] = {
    val regex = "seeds(\\s*):(.*)".r
    regex.findFirstMatchIn(seedsStr)
      .map { m =>
        m.group(2)
          .trim
          .split("\\s+")
          .map(_.toLong)
          .grouped(2)
          .toSeq
          .map { case Array(start, length) =>
            NumericRange[Long](
              start,
              start + length,
              1
            )
          }
          .sortBy(_.start)
      }
      .getOrElse(throw new RuntimeException("Incorrect seeds input string"))
  }
}
