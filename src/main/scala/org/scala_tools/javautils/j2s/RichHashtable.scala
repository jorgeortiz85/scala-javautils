package org.scala_tools.javautils.j2s

import java.util.Hashtable
import scala.collection.jcl.{Hashtable => SHashtable}

class RichHashtable[K, V](map: Hashtable[K, V]) extends RichMap[K, V, Hashtable](map) {
  protected def build[X, Y]: Hashtable[X, Y] = new Hashtable[X, Y]
  override def toScala: SHashtable[K, V] = new SHashtable(map)
}
