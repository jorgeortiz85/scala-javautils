package org.scala_tools.javautils.j2s

import java.util.IdentityHashMap
import scala.collection.jcl.{IdentityHashMap => SIdentityHashMap}

class RichIdentityHashMap[K, V](map: IdentityHashMap[K, V]) extends RichMap[K, V, IdentityHashMap](map) {
  protected def build[X, Y]: IdentityHashMap[X, Y] = new IdentityHashMap[X, Y]
  override def toScala: SIdentityHashMap[K, V] = new SIdentityHashMap(map)
}
