package com.yalingunayer.aoc.solutions

class Solution02aTests extends BaseExpectationTest {
  val solver = Solution02a
  val expectations = Map(
    """
      abcdef
      bababc
      abbcde
      abcccd
      aabcdd
      abcdee
      ababab
    """.stripMargin -> 12)
  define
}

class Solution02bTests extends BaseExpectationTest {
  val solver = Solution02b
  val expectations = Map(
    """
      abcde
      fghij
      klmno
      pqrst
      fguij
      axcye
      wvxyz
    """.stripMargin -> Some("fgij"))
  define
}
