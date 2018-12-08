package com.yalingunayer.aoc.solutions

import com.yalingunayer.aoc.solutions.Solution03a.claims

object Solution03 {
  case class Point(x: Int, y: Int)

  case class Claim(id: Int, coords: Point, size: Point) {
    lazy val coveredArea: Set[Point] = {
      val points = for {
        x <- coords.x until coords.x + size.x
        y <- coords.y until coords.y + size.y
      } yield Point(x, y)

      points.toSet
    }
  }

  lazy val claimPattern = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)".r
}

abstract class Solution03[O] extends Solution[O](3) {
  import Solution03._

  def claims(input: String): Stream[Claim] = input.split("\n")
    .toStream
    .map(_.stripMargin.trim)
    .flatMap {
      case claimPattern(id, x, y, w, h) => Some(Claim(id.toInt, Point(x.toInt, y.toInt), Point(w.toInt, h.toInt)))
      case _ => None
    }

  def overlaps(claims: Stream[Claim]): Set[Point] = {
    claims
      .flatMap(_.coveredArea)
      .foldLeft(Map.empty[Point, Int])((acc, point) => acc.updated(point, acc.getOrElse(point, 0) + 1))
      .filter {
        case (_, i) if i > 1 => true
        case _ => false
      }
      .keySet
  }
}

object Solution03a extends Solution03[Int] {
  override def solveFor(input: String): Int = {
    overlaps(claims(input)).size
  }
}

object Solution03b extends Solution03[Option[Int]] {
  override def solveFor(input: String): Option[Int] = {
    val allClaims = claims(input)
    val overlappingPoints = overlaps(allClaims)

    allClaims.find(_.coveredArea.intersect(overlappingPoints).isEmpty).map(_.id)
  }
}
