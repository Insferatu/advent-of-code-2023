package com.insferatu.aoc2023.task3

import com.insferatu.aoc2023.task3.model.{BagConfiguration, Game}

import scala.io.Source

object Solution {
  def main(args: Array[String]): Unit = {
    val bagConfiguration = BagConfiguration(12, 13, 14)
    val result = Source.fromFile("input_d2.txt")
      .getLines
      .toSeq
      .map(Game.fromString)
      .filter(_.isPossible(bagConfiguration))
      .map(_.id)
      .sum
    println(s"Answer: $result")
  }
}
