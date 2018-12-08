package com.yalingunayer.aoc.solutions

class Solution03aTests extends BaseExpectationTest {
  val solver = Solution03a
  val expectations = Map(
    """
      #1 @ 1,3: 4x4
      #2 @ 3,1: 4x4
      #3 @ 5,5: 2x2
    """.stripMargin -> 4)
  define
}
