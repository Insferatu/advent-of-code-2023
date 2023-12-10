package com.insferatu.aoc2023.task7

import com.insferatu.aoc2023.task7.model.Card

import scala.io.Source

object Solution {
  def main(args: Array[String]): Unit = {
    val result = Source.fromFile("input_d4.txt")
      .getLines
      .toSeq
      .map(Card.fromString)
      .map { card =>
        val intersection = card.cardNumbers.intersect(card.winningNumbers)
        if (intersection.nonEmpty) math.pow(2, intersection.size - 1).toLong
        else 0L
      }
      .sum
    println(s"Answer: $result")
  }
}
