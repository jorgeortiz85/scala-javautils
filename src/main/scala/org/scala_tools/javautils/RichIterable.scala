package org.scala_tools.javautils

import java.lang.Iterable
import Implicits.richIterator

class RichIterable[T](iterable: Iterable[T]) {
  def foreach(fn: T => Unit): Unit =
    iterable.iterator.foreach(fn)
}
