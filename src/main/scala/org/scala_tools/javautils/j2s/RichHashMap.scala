package org.scala_tools.javautils.j2s

import java.util.HashMap
import scala.collection.jcl.{HashMap => SHashMap}

class RichHashMap[K, V](map: HashMap[K, V]) extends RichMap[K, V, HashMap](map) {
  protected def build[X, Y]: HashMap[X, Y] = new HashMap[X, Y]
  override def toScala: SHashMap[K, V] = new SHashMap(map)
}
