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
import scala.collection.mutable.Buffer

trait JBufferWrapper[T] extends JList[T] with JSeqWrapper[T] {
  type Wrapped <: Buffer[T]

  override def add(o: T): Boolean =
    modified(underlying -= o)
  override def add(index: Int, element: T): Unit =
    underlying.insert(index, element)
  override def addAll(c: JCollection[_ <: T]): Boolean =
    modified(Implicits.richJIterable(c).foreach(underlying += _))
  override def addAll(index: Int, c: JCollection[_ <: T]): Boolean =
    modified(underlying.insertAll(index, Implicits.richJIterable(c).toScala))
  override def clear(): Unit =
    underlying.clear()
  override def remove(o: AnyRef): Boolean =
    modified(underlying -= o.asInstanceOf[T])
  override def remove(index: Int): T =
    underlying.remove(index)
  override def removeAll(c: JCollection[_]): Boolean =
    modified(Implicits.richJIterable(c).foreach(underlying -= _.asInstanceOf[T]))
  override def retainAll(c: JCollection[_]): Boolean =
    modified(underlying.toList.remove(c contains _).foreach(underlying -= _))
  override def set(index: Int, element: T): T = {
    val rv = underlying(index)
    underlying(index) = element
    rv
  }

  // override def listIterator(index: Int): JListIterator[T] = new JListIterator[T] {
  //   private var cursor = index
  // 
  //   def hasNext(): Boolean =
  //     cursor < underlying.size
  //   def hasPrevious(): Boolean =
  //     cursor > 0
  //   def nextIndex(): Int =
  //     cursor
  //   def previousIndex(): Int =
  //     cursor - 1
  //   def next(): T = {
  //     val next = nextIndex()
  //     if (hasNext()) cursor += 1
  //     underlying.apply(next)
  //   }
  //   def previous(): T = {
  //     val prev = previousIndex()
  //     if (hasPrevious()) cursor -= 1
  //     underlying.apply(prev)
  //   }
  // 
  // 
  //   def add(o: T): Unit =
  //     throw new UnsupportedOperationException
  //   def remove(): Unit =
  //     throw new UnsupportedOperationException
  //   def set(o: T): Unit =
  //     throw new UnsupportedOperationException
  // }
  // override def subList(fromIndex: Int, toIndex: Int): JList[T] =
  //   Implicits.richSSeq(underlying.projection.slice(fromIndex, toIndex)).toJava
}
