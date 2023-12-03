package com.insferatu.aoc2023.task6

import com.insferatu.aoc2023.task5.kdtree.KDTree
import com.insferatu.aoc2023.task6.model.{LineScanState, NumberScanState}

import scala.io.Source

object Solution {
  def main(args: Array[String]): Unit = {
    val inputLines = Source.fromFile("input_d3.txt")
      .getLines
      .toSeq
    val asteriskPoints = inputLines
      .zipWithIndex
      .toVector
      .map { case (line, lineIndex) =>
        line
          .zipWithIndex
          .flatMap { case (symbol, symbolIndex) =>
            if (symbol == '*') {
              Some(Vector(symbolIndex.toDouble, lineIndex.toDouble))
            } else None
          }
      }
      .flatten
    val tree = KDTree(asteriskPoints)
    val result = inputLines
      .zipWithIndex
      .map { case (line, lineIndex) =>
        (line :+ '.').zipWithIndex
          .foldLeft[LineScanState](LineScanState.initial) { case (lineScanState, (symbol, symbolIndex)) =>
            if (symbol.isDigit) {
              lineScanState.copy(
                numberScanStateOpt = Some(
                  lineScanState
                    .numberScanStateOpt
                    .map(_.getNextNumberScanState(symbol))
                    .getOrElse(NumberScanState(symbolIndex, symbol.toString))
                )
              )
            } else {
              lineScanState.numberScanStateOpt match
                case Some(numberScanState) =>
                  val number = numberScanState.numberInput.toInt
                  val minPoint = Vector(
                    (numberScanState.startIndex - 2).toDouble,
                    (lineIndex - 2).toDouble
                  )
                  val maxPoint = Vector(
                    (numberScanState.startIndex + numberScanState.numberInput.size + 1).toDouble,
                    (lineIndex + 2).toDouble
                  )
                  val nearPoints = tree.range(minPoint, maxPoint)
                  if (nearPoints.nonEmpty)
                    LineScanState(lineScanState.foundNumbersWithNearPoints :++ nearPoints.map(number -> _), None)
                  else
                    LineScanState(lineScanState.foundNumbersWithNearPoints, None)
                case None => lineScanState
            }
          }
          .foundNumbersWithNearPoints
      }
      .flatten
      .groupBy(_._2)
      .filter(_._2.size == 2)
      .view
      .mapValues(_.map(_._1).product)
      .values
      .toSeq
      .sum
    println(s"Answer: $result")
  }
}
