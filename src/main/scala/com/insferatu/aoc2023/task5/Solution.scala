package com.insferatu.aoc2023.task5

import com.insferatu.aoc2023.task5.kdtree.KDTree
import com.insferatu.aoc2023.task5.model.{LineScanState, NumberScanState}

import scala.io.Source

object Solution {
  def main(args: Array[String]): Unit = {
    val inputLines = Source.fromFile("input_d3.txt")
      .getLines
      .toSeq
    val symbolPoints = inputLines
      .zipWithIndex
      .toVector
      .map { case (line, lineIndex) =>
        line
          .zipWithIndex
          .flatMap { case (symbol, symbolIndex) =>
            if (!symbol.isDigit && symbol != '.') {
              Some(Vector(symbolIndex.toDouble, lineIndex.toDouble))
            } else None
          }
      }
      .flatten
    val tree = KDTree(symbolPoints)
    val foundNumbers = inputLines
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
                  if (tree.range(minPoint, maxPoint).nonEmpty)
                    LineScanState(lineScanState.foundNumbers :+ number, None)
                  else
                    LineScanState(lineScanState.foundNumbers, None)
                case None => lineScanState
            }
          }
          .foundNumbers
      }
      .flatten
    val result = foundNumbers.sum
    println(s"Answer: $result")
  }
}
