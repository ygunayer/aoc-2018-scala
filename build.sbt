name := "aoc-2018-scala"

version := "1.0"

scalaVersion := "2.11.7"

lazy val scalaTestVersion = "3.0.5"

libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
)
