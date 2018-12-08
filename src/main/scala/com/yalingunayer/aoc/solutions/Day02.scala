package com.yalingunayer.aoc.solutions

import com.yalingunayer.aoc.Utils
import com.yalingunayer.aoc.solutions.Solution02.KeyLetters

abstract class Solution02[O] extends Solution[O](2) {
  def words(input: String): Stream[String] = input.split("\n")
    .toStream
    .map(_.stripMargin.trim)
    .filter(_.length > 0)
}

object Solution02 {
  case class KeyLetters(twice: Set[Char], thrice: Set[Char]) {
    def add(counts: (Char, Int)): KeyLetters = counts match {
      case (c, 2) => KeyLetters(twice + c, thrice)
      case (c, 3) => KeyLetters(twice, thrice + c)
      case _ => this
    }

    def ordinal(): (Int, Int) = {
      if (twice.nonEmpty && thrice.nonEmpty) (1, 1)
      else if (twice.nonEmpty) (1, 0)
      else if (thrice.nonEmpty) (0, 1)
      else (0, 0)
    }

    def addTo(c: (Int, Int)): (Int, Int) = {
      val (a, b) = this.ordinal()
      (c._1 + a, c._2 + b)
    }
  }

  object KeyLetters {
    def empty(): KeyLetters = KeyLetters(Set(), Set())

    def from(word: String): KeyLetters = countChars(word).foldLeft(KeyLetters.empty)((acc: KeyLetters, cc: (Char, Int)) => acc.add(cc))
  }

  def countChars(word: String): Map[Char, Int] = word.foldLeft(Map.empty[Char, Int])((acc, c) => acc.updated(c, acc.getOrElse(c, 0) + 1))
}

object Solution02a extends Solution02[Int] {
  def solveFor(input: String): Int = {
    val (twice, thrice) = words(input)
      .map(KeyLetters.from)
      .foldLeft((0, 0))((c, keyLetters) => keyLetters.addTo(c))

    twice * thrice
  }
}

object Solution02b extends Solution02[Option[String]] {
  def solveFor(input: String): Option[String] = {
    val asList = words(input).toList
    val comparisonEntries = asList.zipWithIndex.map { case (s, i) => (s, asList.drop(i).tail) }
    val comparisons = comparisonEntries.flatMap {
      case (str, cc) => cc.map(c => (str, c, Utils.StringUtils.editDistance(str, c)))
    }

    val similars = comparisons.filter {
      case (a, b, 1) => true
      case _ => false
    }

    similars.headOption.map {
      case (a, b, _) => {
        println(f"Found similar words: $a <=> $b")
        a.zipWithIndex.filter {
          case (c, i) if b.charAt(i) == c => true
          case _ => false
        } map(_._1) mkString
      }
    }
  }
}
