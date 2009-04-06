package org.scala_tools.javautils.j2s

import java.util.List
import scala.collection.jcl.BufferWrapper
import scala.collection.jcl.Conversions

abstract class RichList[T, C[U] <: List[U]](list: C[T]) extends RichCollection[T, C](list) {
  protected def build[S]: C[S]
  override def toScala: BufferWrapper[T] =
    Conversions.convertList(list)
}
