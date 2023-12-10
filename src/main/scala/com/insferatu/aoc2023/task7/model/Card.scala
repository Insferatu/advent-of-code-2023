package com.insferatu.aoc2023.task7.model

case class Card(id: Int, winningNumbers: Set[Int], cardNumbers: Set[Int])

object Card {
  def fromString(str: String): Card = {
    val regex = "Card(\\s+)([1-9][0-9]*):(.*)".r
    regex.findFirstMatchIn(str)
      .map { m =>
        val Array(winningNumbersStr, cardNumbersStr) = m.group(3).split('|')
        val winningNumbers = winningNumbersStr.trim.split("\\s+").map(_.toInt).toSet
        val cardNumbers = cardNumbersStr.trim.split("\\s+").map(_.toInt).toSet
        Card(
          m.group(2).toInt,
          winningNumbers,
          cardNumbers
        )
      }
      .getOrElse(throw new RuntimeException("Incorrect card input string"))
  }
}
