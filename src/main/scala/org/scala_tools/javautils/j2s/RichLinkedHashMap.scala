package org.scala_tools.javautils.j2s

import java.util.LinkedHashMap
import scala.collection.jcl.{LinkedHashMap => SLinkedHashMap}

class RichLinkedHashMap[K, V](map: LinkedHashMap[K, V]) extends RichMap[K, V, LinkedHashMap](map) {
  protected def build[X, Y] = new LinkedHashMap[X, Y]
  override def toScala: SLinkedHashMap[K, V] = new SLinkedHashMap(map)
}
