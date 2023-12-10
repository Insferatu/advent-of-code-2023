package com.insferatu.aoc2023.task9.model

import scala.collection.immutable.NumericRange

case class RangeMergeState(collectedRanges: Seq[NumericRange[Long]], previousRangeOpt: Option[NumericRange[Long]]) {
  def getAllCollectedRanges: Seq[NumericRange[Long]] = collectedRanges :++ previousRangeOpt
}

object RangeMergeState {
  def init: RangeMergeState = RangeMergeState(Seq.empty, None)
}
