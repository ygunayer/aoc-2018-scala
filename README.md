# aoc-2018-scala
My solutions for [Advent of Code 2018](http://adventofcode.com/2018), in Scala. Based on my previous implementation for [AoC 2017](https://github.com/ygunayer/aoc-2017-scala).

[![Build Status](https://travis-ci.org/ygunayer/aoc-2018-scala.svg?branch=master)](https://travis-ci.org/ygunayer/aoc-2018-scala) [![Coverage Status](https://coveralls.io/repos/github/ygunayer/aoc-2017-scala/badge.svg?branch=master)](https://coveralls.io/github/ygunayer/aoc-2018-scala?branch=master)

## Running
Use the following command to run a specific challenge (mind the double quotes)

```
$ sbt "run 01a"
```

## Testing
Each solution has tests that use the examples as input. To run all tests, run the following command

```
$ sbt test
```

To run a specific test, launch `sbt` in continuous execution mode, and run the following command (mind the asterisk `*`)

```
> testOnly *Solution01bTests
```

## License
MIT
