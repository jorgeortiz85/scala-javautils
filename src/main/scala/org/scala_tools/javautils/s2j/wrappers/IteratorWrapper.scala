package org.scala_tools.javautils.s2j.wrappers

import java.util.{Iterator => JIterator}
import scala.{Iterator => SIterator}

class IteratorWrapper[T](val underlying: SIterator[T]) extends JIterator[T] {
  def hasNext: Boolean = underlying.hasNext
  def next(): T = underlying.next
  def remove() = throw new UnsupportedOperationException
  def toScala = underlying
}
