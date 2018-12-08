package com.yalingunayer.aoc.solutions

import com.yalingunayer.aoc.Utils

abstract class Solution01 extends Solution[Int](1) {
  def numbers(input: String): Stream[Int] = input.split("\n")
    .toStream
    .map(_.stripMargin.trim)
    .filter(_.length > 0)
    .map(_.toInt)
}

object Solution01a extends Solution01 {
  def solveFor(input: String): Int = numbers(input).sum
}

case class Solution01bIterator(isFirst: Boolean, sum: Int, seen: Map[Int, Int]) {
  def add(n: Int): Either[Int, Solution01bIterator] = {
    val newSum = sum + n
    val newSumSeen = seen.getOrElse(newSum, 0) + (if (isFirst) 0 else 1)

    if (newSumSeen > 1) {
      Left(newSum)
    } else {
      Right(Solution01bIterator(isFirst = false, newSum, seen.updated(newSum, newSumSeen)))
    }
  }
}

object Solution01b extends Solution01 {
  def solveFor(input: String): Int = {
    val numbersRepeated = Stream.continually(numbers(input)).flatten

    def go(str: Stream[Int], isFirst: Boolean, sum: Int, seen: Map[Int, Int]): Int = str match {
      case n #:: rest => {
        val newSum = sum + n
        val newSumSeen = seen.getOrElse(newSum, 0) + (if (isFirst) 0 else 1)
        val nextSeen = seen.updated(newSum, newSumSeen)

        if (newSumSeen > 1) {
          newSum
        } else {
          go(rest, false, newSum, nextSeen)
        }
      }
      case _ => throw new Exception("What the fuck")
    }

    go(numbersRepeated, true, 0, Map.empty)
  }
}
