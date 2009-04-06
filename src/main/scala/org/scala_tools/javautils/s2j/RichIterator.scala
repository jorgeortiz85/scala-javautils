package org.scala_tools.javautils.s2j

import java.util.{Iterator => JIterator}
import wrappers._

class RichIterator[T](iterator: Iterator[T]) {
  def toJava: JIterator[T] = new IteratorWrapper(iterator)
}
