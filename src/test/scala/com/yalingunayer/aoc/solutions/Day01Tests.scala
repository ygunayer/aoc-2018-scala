package com.yalingunayer.aoc.solutions

import org.scalatest._
import org.scalatest.FlatSpec

class Solution01aTests extends BaseExpectationTest {
  val solver = Solution01a
  val expectations = Map(
"""
  +1
  -1
""".stripMargin -> 0)
  define
}

class Solution01bTests extends BaseExpectationTest {
  val solver = Solution01b
  val expectations = Map(
    """
  +1
  -1
""".stripMargin -> 0)
  define
}
