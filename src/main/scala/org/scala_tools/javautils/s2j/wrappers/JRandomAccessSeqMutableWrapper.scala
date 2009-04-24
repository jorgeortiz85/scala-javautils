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
package org.scala_tools.javautils.s2j.wrappers

import java.lang.{Iterable => JIterable}
import java.util.{Iterator => JIterator, List => JList, Collection => JCollection,
  ListIterator => JListIterator}

trait JRandomAccessSeqMutableWrapper[T] extends JList[T] with JSeqWrapper[T] {
  type Wrapped <: RandomAccessSeq.Mutable[T]

  // TODO: Implement
  override def add(index: Int, element: T): Unit =
    throw new UnsupportedOperationException
  override def addAll(index: Int, c: JCollection[_ <: T]): Boolean =
    throw new UnsupportedOperationException
  override def remove(index: Int): T =
    throw new UnsupportedOperationException
  override def set(index: Int, element: T): T = {
    val rv = underlying(index)
    underlying(index) = element
    rv
  }

  override def add(o: T): Boolean =
    throw new UnsupportedOperationException
  override def addAll(c: JCollection[_ <: T]): Boolean =
    throw new UnsupportedOperationException
  override def clear(): Unit =
    throw new UnsupportedOperationException
  override def remove(o: AnyRef): Boolean =
    throw new UnsupportedOperationException
  override def removeAll(c: JCollection[_]): Boolean =
    throw new UnsupportedOperationException
  override def retainAll(c: JCollection[_]): Boolean =
    throw new UnsupportedOperationException
}
