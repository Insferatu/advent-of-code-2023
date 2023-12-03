package com.insferatu.aoc2023.task3.model

case class Game(id: Int, outcomes: Seq[Outcome]) {
  def isPossible(bagConfiguration: BagConfiguration): Boolean = {
    outcomes.forall(_.isPossible(bagConfiguration))
  }

  def minBagConfiguration: BagConfiguration = BagConfiguration(
    outcomes.map(_.redCount).max,
    outcomes.map(_.greenCount).max,
    outcomes.map(_.blueCount).max
  )
}

object Game {
  def fromString(str: String): Game = {
    val regex = "Game ([1-9][0-9]*):(.*)".r
    regex.findFirstMatchIn(str)
      .map { m =>
        Game(
          m.group(1).toInt,
          m.group(2).split(";").map(_.trim).map(Outcome.fromString).toSeq
        )
      }
      .getOrElse(throw new RuntimeException("Incorrect game input string"))
  }
}
