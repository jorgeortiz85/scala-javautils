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
  List, Map, Set, Collection}

object Builders extends Builders

trait Builders {
  implicit def BuildArrayList[T] = Build(new ArrayList[T])
  implicit def BuildHashSet[T] = Build(new HashSet[T])
  implicit def BuildHashMap[K, V] = Build(new HashMap[K, V])
  implicit def BuildHashtable[K, V] = Build(new Hashtable[K, V])
  implicit def BuildIdentityHashMap[K, V] = Build(new IdentityHashMap[K, V])
  implicit def BuildLinkedHashMap[K, V] = Build(new LinkedHashMap[K, V])
  implicit def BuildLinkedHashSet[T] = Build(new LinkedHashSet[T])
  implicit def BuildLinkedList[T] = Build(new LinkedList[T])
  implicit def BuildTreeMap[K, V] = Build(new TreeMap[K, V])
  implicit def BuildTreeSet[T] = Build(new TreeSet[T])
  implicit def BuildWeakHashMap[K, V] = Build(new WeakHashMap[K, V])

  implicit def BuildList[T]: Build[List[T]] = Build(new ArrayList[T])

  // implicit def richJEnumeration[T](enumeration: Enumeration[T]) = new RichJEnumeration(enumeration)
  // implicit def richJIterator[T](iterator: Iterator[T]) = new RichJIterator(iterator)
  // implicit def richJIterable[T](iterable: Iterable[T]) = new RichJIterable(iterable)
  // implicit def richJCollection[T](collection: Collection[T]) = new RichJCollection(collection)
  // implicit def richJList[T](list: List[T]) = new RichJList(list)
  // implicit def richJSet[T](set: Set[T]) = new RichJSet(set)
  // implicit def richJMap[K, V](map: Map[K, V]) = new RichJMap(map)
}