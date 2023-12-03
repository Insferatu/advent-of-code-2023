package com.insferatu.aoc2023.task6.model

case class LineScanState(
  foundNumbersWithNearPoints: Seq[(Int, Vector[Double])],
  numberScanStateOpt: Option[NumberScanState]
)

object LineScanState {
  def initial: LineScanState = LineScanState(Seq(), None)
}

case class NumberScanState(
  startIndex: Int,
  numberInput: String
) {
  def getNextNumberScanState(symbol: Char): NumberScanState = NumberScanState(
    startIndex,
    numberInput.appended(symbol)
  )
}
