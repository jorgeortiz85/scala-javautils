/**
 * Copyright 2009 Jorge Ortiz
 * Copyright 2009 Kris Nuttycombe
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
package org.scala_tools.javautils.s2j

import scala.collection.{Set, Map, SortedSet}
import scala.collection.mutable.{Set => MSet, Map => MMap, Buffer}

object SImplicits extends SImplicits

trait SImplicits {
  implicit def RichSIterator[T](iterator: Iterator[T]): RichSIterator[T] = new RichSIterator(iterator)
  implicit def RichSIterable[T](iterable: Iterable[T]): RichSIterable[T] = new RichSIterable(iterable)
  implicit def RichSCollection[T](collection: Collection[T]): RichSCollection[T] = new RichSCollection(collection)
  implicit def RichSSeq[T](seq: Seq[T]): RichSSeq[T] = new RichSSeq(seq)
  implicit def RichSRandomAccessSeqMutable[T](seq: RandomAccessSeq.Mutable[T]): RichSRandomAccessSeqMutable[T] = new RichSRandomAccessSeqMutable(seq)
  implicit def RichSBuffer[T](buffer: Buffer[T]): RichSBuffer[T] = new RichSBuffer(buffer)
  implicit def RichSSet[T](set: Set[T]): RichSSet[T] = new RichSSet(set)
  implicit def RichSSortedSet[T](set: SortedSet[T]): RichSSortedSet[T] = new RichSSortedSet(set)
  implicit def RichSMutableSet[T](set: MSet[T]): RichSMutableSet[T] = new RichSMutableSet(set)
  implicit def RichSMap[K, V](map: Map[K, V]): RichSMap[K, V] = new RichSMap(map)
  implicit def RichSMutableMap[K, V](map: MMap[K, V]): RichSMutableMap[K, V] = new RichSMutableMap(map)
}
