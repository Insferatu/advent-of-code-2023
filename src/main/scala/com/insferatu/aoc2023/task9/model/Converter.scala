package com.insferatu.aoc2023.task9.model

import scala.Function.tupled
import scala.collection.immutable.NumericRange

case class Converter(name: String, rangeMapping: Seq[(NumericRange[Long], NumericRange[Long])]) {
  def convertVal(input: Long): Long = {
    rangeMapping
      .find { case (sourceRange, _) => sourceRange.contains(input) }
      .map(tupled(convert(input, _, _)))
      .getOrElse(input)
  }

  private def convert(input: Long, sourceRange: NumericRange[Long], destinationRange: NumericRange[Long]): Long =
      input - sourceRange.start + destinationRange.start

  def convertValSeq(inputSeq: Seq[Long]): Seq[Long] = {
    inputSeq.map(convertVal)
  }

  def convertRange(inputRange: NumericRange[Long]): Seq[NumericRange[Long]] = {
    rangeMapping
      .filter { case (sourceRange, _) =>
        checkRangeIntersection(inputRange, sourceRange)
      }
      .foldLeft(RangeConversionState.init(inputRange.start)) {
        case (state, (currentSourceRange, currentDestinationRange)) =>
          val (currentNumber, producedOutputRanges) = if (currentSourceRange.contains(state.currentNumber)) {
            (state.currentNumber, Seq.empty[NumericRange[Long]])
          } else {
            (currentSourceRange.start, Seq(NumericRange[Long](state.currentNumber, currentSourceRange.start, 1)))
          }
          val end = math.min(currentSourceRange.end, inputRange.end)
          val transformedRange = NumericRange[Long](
            convert(currentNumber, currentSourceRange, currentDestinationRange),
            convert(end, currentSourceRange, currentDestinationRange),
            1
          )
          RangeConversionState(
            end,
            state.outputRanges ++ (producedOutputRanges :+ transformedRange)
          )
      }
      .getFinalOutputRanges(inputRange.end)
  }

  def convertRangeSeq(inputRangeSeq: Seq[NumericRange[Long]]): Seq[NumericRange[Long]] =
    inputRangeSeq.map(convertRange)
      .flatten
      .sortBy(_.start)
      .foldLeft(RangeMergeState.init) { case (mergeState, currentRange) =>
        mergeState.previousRangeOpt
          .map { previousRange =>
            if (previousRange.end == currentRange.start) {
              val newRange = NumericRange[Long](
                previousRange.start,
                currentRange.end,
                1
              )
              mergeState.copy(previousRangeOpt = Some(newRange))
            } else {
              RangeMergeState(
                mergeState.collectedRanges :+ previousRange,
                Some(currentRange)
              )
            }
          }
          .getOrElse(mergeState.copy(previousRangeOpt = Some(currentRange)))
      }
      .getAllCollectedRanges

  private def checkRangeIntersection(r1: NumericRange[Long], r2: NumericRange[Long]): Boolean =
    r1.contains(r2.start) || r2.contains(r1.end - 1) || r1.contains(r2.end - 1) || r2.contains(r1.start)

}

object Converter {
  def fromString(str: String): Converter = {
    val head +: rangeLines = str.split("\n").toSeq: @unchecked
    val name = head.stripSuffix(":")
    val rangeMapping = rangeLines
      .map { rangeLine =>
        val Array(destinationRangeStart, sourceRangeStart, rangeLength) = rangeLine.trim.split("\\s+").map(_.toLong)
        val sourceRange = NumericRange[Long](sourceRangeStart, sourceRangeStart + rangeLength, 1)
        val destinationRange = NumericRange[Long](destinationRangeStart, destinationRangeStart + rangeLength, 1)
        sourceRange -> destinationRange
      }
      .sortBy { case (sourceRange, _) => sourceRange.start }
    Converter(name, rangeMapping)
  }
}
