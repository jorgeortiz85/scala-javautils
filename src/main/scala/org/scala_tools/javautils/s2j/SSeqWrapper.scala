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

import java.lang.{Iterable => JIterable}
import java.util.{Iterator => JIterator, List => JList, Collection => JCollection,
  ListIterator => JListIterator}

trait SSeqWrapper[T] extends JList[T] with SCollectionWrapper[T] {
  type Wrapped <: Seq[T]
  
  def get(index: Int): T =
    underlying.apply(index)
  def indexOf(o: AnyRef): Int =
    underlying.indexOf(o)
  def lastIndexOf(o: AnyRef): Int =
    underlying.lastIndexOf(o)
  def listIterator(): JListIterator[T] =
    listIterator(0)
  def listIterator(index: Int): JListIterator[T] = new JListIterator[T] {
    private var cursor = index

    def hasNext(): Boolean =
      cursor < underlying.size
    def hasPrevious(): Boolean =
      cursor > 0
    def nextIndex(): Int =
      cursor
    def previousIndex(): Int =
      cursor - 1
    def next(): T = {
      val next = nextIndex()
      if (hasNext()) cursor += 1
      underlying.apply(next)
    }
    def previous(): T = {
      val prev = previousIndex()
      if (hasPrevious()) cursor -= 1
      underlying.apply(prev)
    }


    def add(o: T): Unit =
      throw new UnsupportedOperationException
    def remove(): Unit =
      throw new UnsupportedOperationException
    def set(o: T): Unit =
      throw new UnsupportedOperationException
  }
  def subList(fromIndex: Int, toIndex: Int): JList[T] =
    Implicits.RichSSeq(underlying.projection.slice(fromIndex, toIndex)).asJava

  def add(index: Int, element: T): Unit =
    throw new UnsupportedOperationException
  def addAll(index: Int, c: JCollection[_ <: T]): Boolean =
    throw new UnsupportedOperationException
  def remove(index: Int): T =
    throw new UnsupportedOperationException
  def set(index: Int, element: T): T =
    throw new UnsupportedOperationException
}
