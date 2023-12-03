package com.insferatu.aoc2023.task5.kdtree

object Types {
  type Point = Vector[Double]
  type Points = Vector[Point]

  def Point(xs: Double*): Vector[Double] = Vector(xs: _*)

  def Points(xs: Point*): Vector[Point] = Vector(xs: _*)
}
