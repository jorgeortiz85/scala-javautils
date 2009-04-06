package org.scala_tools.javautils.j2s

import java.util.TreeMap
import scala.collection.jcl.{TreeMap => STreeMap}

class RichTreeMap[K, V](map: TreeMap[K, V]) extends RichMap[K, V, TreeMap](map) {
  protected def build[X, Y]: TreeMap[X, Y] = new TreeMap[X, Y]
}
