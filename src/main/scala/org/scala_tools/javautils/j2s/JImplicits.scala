/**
 * Copyright 2009 Jorge Ortiz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 *
 **/
package org.scala_tools.javautils.j2s

import java.lang.Iterable
import java.util.{Iterator, ArrayList, HashSet, HashMap, Enumeration, Hashtable,
  IdentityHashMap, LinkedHashMap, LinkedHashSet, LinkedList, TreeMap, TreeSet, WeakHashMap,
  List, Map, Set, Collection, Deque}

object JImplicits extends JImplicits

trait JImplicits extends Builders {
  implicit def richJEnumeration[T](enumeration: Enumeration[T]) = new RichJEnumeration(enumeration)
  implicit def richJIterator[T](iterator: Iterator[T]) = new RichJIterator(iterator)
  implicit def richJIterable[T](iterable: Iterable[T]) = new RichJIterable(iterable)
  implicit def richJCollection[T, C[U] <: Collection[U]](collection: C[T]) = new RichJCollection[T, C](collection)
  implicit def richJList[T](list: List[T]) = new RichJList(list)
  implicit def richJSet[T](set: Set[T]) = new RichJSet(set)
  implicit def richJMap[K, V](map: Map[K, V]) = new RichJMap(map)
  implicit def richJListWithDeque[T](lwd: List[T] with Deque[T]) = new RichJListWithDeque(lwd)

  // implicit def richJArrayList[T](list: ArrayList[T]) = new RichArrayList(list)
  // implicit def richJHashSet[T](set: HashSet[T]) = new RichHashSet(set)
  // implicit def richJHashMap[K, V](map: HashMap[K, V]) = new RichHashMap(map)  
  // implicit def richJHashtable[K, V](map: Hashtable[K, V]) = new RichHashtable(map)  
  // implicit def richJIdentityHashMap[K, V](map: IdentityHashMap[K, V]) = new RichIdentityHashMap(map)  
  // implicit def richJLinkedHashMap[K, V](map: LinkedHashMap[K, V]) = new RichLinkedHashMap(map)  
  // implicit def richJLinkedHashSet[T](set: LinkedHashSet[T]) = new RichLinkedHashSet(set)
  // implicit def richJLinkedList[T](list: LinkedList[T]) = new RichLinkedList(list)
  // implicit def richJTreeMap[K, V](map: TreeMap[K, V]) = new RichTreeMap(map)
  // implicit def richJTreeSet[T](set: TreeSet[T]) = new RichTreeSet(set)
  // implicit def richJWeakHashMap[K, V](map: WeakHashMap[K, V]) = new RichWeakHashMap(map)
}
