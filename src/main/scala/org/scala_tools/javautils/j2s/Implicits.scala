package org.scala_tools.javautils.j2s

import java.lang.Iterable
import java.util.{Iterator, ArrayList, HashSet, HashMap, Enumeration, Hashtable,
  IdentityHashMap, LinkedHashMap, LinkedHashSet, LinkedList, TreeMap, TreeSet, WeakHashMap,
  List, Map, Set}

object Implicits extends Implicits

trait Implicits {
  implicit def richJEnumeration[T](enumeration: Enumeration[T]) = new RichEnumeration(enumeration)
  implicit def richJIterator[T](iterator: Iterator[T]) = new RichIterator(iterator)
  implicit def richJIterable[T](iterable: Iterable[T]) = new RichIterable(iterable)
  implicit def richJArrayList[T](list: ArrayList[T]) = new RichArrayList(list)
  implicit def richJHashSet[T](set: HashSet[T]) = new RichHashSet(set)
  implicit def richJHashMap[K, V](map: HashMap[K, V]) = new RichHashMap(map)  
  implicit def richJHashtable[K, V](map: Hashtable[K, V]) = new RichHashtable(map)  
  implicit def richJIdentityHashMap[K, V](map: IdentityHashMap[K, V]) = new RichIdentityHashMap(map)  
  implicit def richJLinkedHashMap[K, V](map: LinkedHashMap[K, V]) = new RichLinkedHashMap(map)  
  implicit def richJLinkedHashSet[T](set: LinkedHashSet[T]) = new RichLinkedHashSet(set)
  implicit def richJLinkedList[T](list: LinkedList[T]) = new RichLinkedList(list)
  implicit def richJTreeMap[K, V](map: TreeMap[K, V]) = new RichTreeMap(map)
  implicit def richJTreeSet[T](set: TreeSet[T]) = new RichTreeSet(set)
  implicit def richJWeakHashMap[K, V](map: WeakHashMap[K, V]) = new RichWeakHashMap(map)
  implicit def richJList[T](list: List[T]): RichList[T, List] = new RichList[T, List](list) {
    def build[S]: List[S] = new ArrayList[S]
  }
  implicit def richJSet[T](set: Set[T]): RichSet[T, Set] = new RichSet[T, Set](set) {
    def build[S]: Set[S] = new HashSet[S]
  }
  implicit def richJMap[K, V](map: Map[K, V]): RichMap[K, V, Map] = new RichMap[K, V, Map](map) {
    def build[X, Y]: Map[X, Y] = new HashMap[X, Y]
  }
}
