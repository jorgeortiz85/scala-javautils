package org.scala_tools.javautils.j2s

import java.util.LinkedHashSet
import scala.collection.jcl.{LinkedHashSet => SLinkedHashSet}

class RichLinkedHashSet[T](set: LinkedHashSet[T]) extends RichSet[T, LinkedHashSet](set) {
  protected def build[S] = new LinkedHashSet[S]
  override def toScala: SLinkedHashSet[T] = new SLinkedHashSet(set)
}
