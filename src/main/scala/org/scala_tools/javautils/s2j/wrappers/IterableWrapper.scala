package org.scala_tools.javautils.s2j.wrappers

import java.lang.{Iterable => JIterable}

class IterableWrapper[T](val underlying: Iterable[T]) extends JIterable[T] {
  def iterator = Implicits.richSIterator(underlying.elements).toJava
}
