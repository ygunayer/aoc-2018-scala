package com.yalingunayer.aoc

import scala.io.Source
import scala.math.{max,min}

object Utils {
  object InputUtils {
    def loadFile(name: String): String = {
      val res = getClass.getResource(name)
      val path = res.toString()
      Source.fromURL(path).mkString("")
    }
  }

  object ReflectionUtils {
    def getObjectInstance[A](fullName: String): Option[A] = {
      val mirror = scala.reflect.runtime.universe.runtimeMirror(getClass.getClassLoader)
      val module = mirror.staticModule(fullName)
      val obj = mirror.reflectModule(module)

      Option(obj).map(_.instance).map(_.asInstanceOf[A])
    }
  }

  object StringUtils {
    def editDistance[A](a: Iterable[A], b: Iterable[A]) =
      ((0 to b.size).toList /: a)((prev, x) =>
        (prev zip prev.tail zip b).scanLeft(prev.head + 1) {
          case (h, ((d, v), y)) => min(min(h + 1, v + 1), d + (if (x == y) 0 else 1))
        }) last
  }
}
