package org.scala_tools.javautils.j2s

import java.util.Set
import scala.collection.jcl.{SetWrapper, Conversions}

abstract class RichSet[T, C[U] <: Set[U]](set: C[T]) extends RichCollection[T, C](set) {
  override def toScala: SetWrapper[T] = Conversions.convertSet(set)
  protected def build[S]: C[S]
}
