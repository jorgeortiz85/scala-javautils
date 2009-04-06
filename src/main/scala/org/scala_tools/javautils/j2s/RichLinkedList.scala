package org.scala_tools.javautils.j2s

import java.util.LinkedList
import scala.collection.jcl.{LinkedList => SLinkedList}

class RichLinkedList[T](list: LinkedList[T]) extends RichList[T, LinkedList](list) {
  protected def build[S] = new LinkedList[S]
  override def toScala: SLinkedList[T] = new SLinkedList(list)
}
