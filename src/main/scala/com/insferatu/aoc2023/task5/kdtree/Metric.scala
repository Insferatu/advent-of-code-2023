package com.insferatu.aoc2023.task5.kdtree

import com.insferatu.aoc2023.task5.kdtree.Types.Point

object Metric {
  def minkowski(src: Point, dst: Point, p: Double): Double =
    src.zip(dst).map(point => math.pow(math.abs(point._1 - point._2), p)).sum
}
