package org.scala_tools.javautils.j2s

import java.lang.Iterable
import scala.{Iterable => SIterable}

class RichIterable[T](iterable: Iterable[T]) {
  def foreach(fn: T => Unit): Unit =
    Implicits.richJIterator(iterable.iterator).foreach(fn)

  // TODO: Should toScala always perform in O(1) time?
  def toScala: SIterable[T] =
    Implicits.richJIterator(iterable.iterator).toScala.toList
}
