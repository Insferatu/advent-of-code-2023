package com.insferatu.aoc2023.task9.model

import scala.collection.immutable.NumericRange

case class RangeConversionState(currentNumber: Long, outputRanges: Seq[NumericRange[Long]]) {
  def getFinalOutputRanges(rangeEnd: Long): Seq[NumericRange[Long]] = {
    if (currentNumber < rangeEnd) {
      outputRanges :+ NumericRange[Long](
        currentNumber,
        rangeEnd,
        1
      )
    } else {
      outputRanges
    }
  }
}

object RangeConversionState {
  def init(number: Long): RangeConversionState =
    RangeConversionState(number, Seq.empty)
}