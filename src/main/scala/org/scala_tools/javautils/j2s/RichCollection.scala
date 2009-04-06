package org.scala_tools.javautils.j2s

import java.util.Collection
import scala.{Iterable => SIterable, Collection => SCollection}

abstract class RichCollection[T, C[U] <: Collection[U]](collection: C[T]) extends RichIterable(collection) {
  protected def build[S]: C[S]

  // TODO: Should toScala always perform in O(1) time?
  override def toScala: SCollection[T] =
    Implicits.richJIterator(collection.iterator).toScala.toList

  def filter(fn: T => Boolean): C[T] = {
    val rv = build[T]
    this.foreach { e =>
      if (fn(e))
        rv.add(e)
    }
    rv
  }

  def map[S](fn: T => S): C[S] = {
    val rv = build[S]
    this.foreach { e =>
      rv.add(fn(e))
    }
    rv
  }

  def flatMap[S](fn: T => SIterable[S]): C[S] = {
    val rv = build[S]
    this.foreach { e =>
      fn(e).foreach(rv add _)
    }
    rv
  }
}
