package com.insferatu.aoc2023.task4

import com.insferatu.aoc2023.task3.model.{BagConfiguration, Game}

import scala.io.Source

object Solution {
  def main(args: Array[String]): Unit = {
    val result = Source.fromFile("input_d2.txt")
      .getLines
      .toSeq
      .map(Game.fromString)
      .map(_.minBagConfiguration.power)
      .sum
    println(s"Answer: $result")
  }
}