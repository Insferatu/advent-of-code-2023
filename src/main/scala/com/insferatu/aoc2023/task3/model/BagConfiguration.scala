package com.insferatu.aoc2023.task3.model

case class BagConfiguration(redCount: Int, greenCount: Int, blueCount: Int) {
  def power: Int = redCount * greenCount * blueCount
}
