package org.scala_tools.javautils.j2s

import java.util.WeakHashMap
import scala.collection.jcl.{WeakHashMap => SWeakHashMap}

class RichWeakHashMap[K, V](map: WeakHashMap[K, V]) extends RichMap[K, V, WeakHashMap](map) {
  protected def build[X, Y]: WeakHashMap[X, Y] = new WeakHashMap[X, Y]
  override def toScala: SWeakHashMap[K, V] = new SWeakHashMap(map)
}
