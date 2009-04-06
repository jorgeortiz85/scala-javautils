package org.scala_tools.javautils

import java.lang.Iterable
import java.util.{Iterator, ArrayList, HashSet, HashMap}

object Implicits {
  implicit def richIterator[T](iterator: Iterator[T]) = new RichIterator(iterator)
  implicit def richIterable[T](iterable: Iterable[T]) = new RichIterable(iterable)
  implicit def richArrayList[T](list: ArrayList[T]) = new RichArrayList(list)
  implicit def richHashSet[T](set: HashSet[T]) = new RichHashSet(set)
  implicit def richHashMap[K, V](map: HashMap[K, V]) = new RichHashMap(map)
}
