package com.insferatu.aoc2023.task8

import com.insferatu.aoc2023.task7.model.Card

import scala.io.Source
import cats.syntax.semigroup._

object Solution {
  def main(args: Array[String]): Unit = {
    val cards = Source.fromFile("input_d4.txt")
      .getLines
      .toSeq
      .map(Card.fromString)
    val initialMap = cards.map(_.id -> 1).toMap
    val result = cards
      .foldLeft(initialMap) { case (countMap, card) =>
        val multiplier = countMap(card.id)
        val intersectionSize = card.cardNumbers.intersect(card.winningNumbers).size
        val cardMap = (card.id + 1 to card.id + intersectionSize).map(_ -> multiplier).toMap
        countMap |+| cardMap
      }
      .values
      .sum
    println(s"Answer: $result")
  }
}
