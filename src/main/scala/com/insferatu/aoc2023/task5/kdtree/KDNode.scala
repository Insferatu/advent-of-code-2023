package com.insferatu.aoc2023.task5.kdtree

import com.insferatu.aoc2023.task5.kdtree.Types.Point

case class KDNode(point: Point, lChild: Option[KDNode], rChild: Option[KDNode])
