package org.scala_tools.javautils.j2s

import java.util.TreeSet
import scala.collection.jcl.{TreeSet => STreeSet}

class RichTreeSet[T](set: TreeSet[T]) extends RichSet[T, TreeSet](set) {
  protected def build[S]: TreeSet[S] = new TreeSet[S]
}
