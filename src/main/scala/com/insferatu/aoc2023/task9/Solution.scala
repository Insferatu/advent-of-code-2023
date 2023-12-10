package com.insferatu.aoc2023.task9

import com.insferatu.aoc2023.task9.model.Converter

import scala.io.Source

object Solution {
  def main(args: Array[String]): Unit = {
    val seedsStr +: converterStrSeq = Source.fromFile("input_d5.txt")
      .getLines
      .mkString("\n")
      .split("\n\n")
      .toSeq: @unchecked
    val seedsSeq = extractSeedsSeq(seedsStr)
    val converterSeq = converterStrSeq.map(Converter.fromString)
    val result = converterSeq
      .foldLeft(seedsSeq) { case (input, converter) => converter.convertValSeq(input) }
      .min
    println(s"Answer: $result")
  }

  def extractSeedsSeq(seedsStr: String): Seq[Long] = {
    val regex = "seeds(\\s*):(.*)".r
    regex.findFirstMatchIn(seedsStr)
      .map(_.group(2).trim.split("\\s+").map(_.toLong).toSeq)
      .getOrElse(throw new RuntimeException("Incorrect seeds input string"))
  }
}
