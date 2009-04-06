package org.scala_tools.javautils.j2s

import java.util.Iterator
import scala.{Iterator => SIterator}

class RichIterator[T](iterator: Iterator[T]) {
  def foreach(fn: T => Unit): Unit =
    while (iterator.hasNext)
      fn(iterator.next)

  def toScala: SIterator[T] = new SIterator[T] {
    def hasNext = iterator.hasNext
    def next() = iterator.next
  }
}
