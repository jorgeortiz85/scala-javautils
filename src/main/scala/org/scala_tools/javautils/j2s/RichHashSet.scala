package org.scala_tools.javautils.j2s

import java.util.HashSet
import scala.collection.jcl.{HashSet => SHashSet}

class RichHashSet[T](set: HashSet[T]) extends RichSet[T, HashSet](set) {
  protected def build[S]: HashSet[S] = new HashSet[S]
  override def toScala: SHashSet[T] = new SHashSet(set)
}
