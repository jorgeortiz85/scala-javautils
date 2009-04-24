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
package org.scala_tools.javautils.s2j

import scala.collection.{Set, Map}
import scala.collection.mutable.{Set => MSet, Map => MMap, Buffer}

object SImplicits extends SImplicits

trait SImplicits {
  implicit def richSIterator[T](iterator: Iterator[T]) = new RichSIterator(iterator)
  implicit def richSIterable[T](iterable: Iterable[T]) = new RichSIterable(iterable)
  implicit def richSCollection[T](collection: Collection[T]) = new RichSCollection(collection)
  implicit def richSSeq[T](seq: Seq[T]) = new RichSSeq(seq)
  implicit def richSRandomAccessSeqMutable[T](seq: RichSRandomAccessSeqMutable[T]) = new RichSRandomAccessSeqMutable(seq)
  implicit def richSBuffer[T](buffer: Buffer[T]) = new RichSBuffer(buffer)
  implicit def richSSet[T](set: Set[T]) = new RichSSet(set)
  implicit def richSMutableSet[T](set: MSet[T]) = new RichSMutableSet(set)
  implicit def richSMap[K, V](map: Map[K, V]) = new RichSMap(map)
  implicit def richSMutableMap[K, V](map: MMap[K, V]) = new RichSMutableMap(map)
}
