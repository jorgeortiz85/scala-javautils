package org.scala_tools.javautils

import java.util.Iterator

class RichIterator[T](iterator: Iterator[T]) {
  def foreach(fn: T => Unit): Unit =
    while (iterator.hasNext)
      fn(iterator.next)
}
