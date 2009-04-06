package org.scala_tools.javautils.j2s

import java.util.ArrayList
import scala.collection.jcl.{ArrayList => SArrayList}

class RichArrayList[T](list: ArrayList[T]) extends RichList[T, ArrayList](list) {
  protected def build[S] = new ArrayList[S]
  override def toScala: SArrayList[T] = new SArrayList(list)
}
