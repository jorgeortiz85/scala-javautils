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
import java.util.{Iterator => JIterator, Collection => JCollection}

trait SCollectionWrapper[T] extends JCollection[T] with SIterableWrapper[T] {
  type Wrapped <: Collection[T]

  // This helper method takes an action and returns a Boolean indicating
  // whether the underlying Collection was modified as a result of the action.
  protected def modified(action: => Unit): Boolean = {
    val oldSize = underlying.size
    action
    oldSize != underlying.size
  }

  def contains(o: AnyRef): Boolean =
    underlying.exists(_ == o)
  def containsAll(c: JCollection[_]): Boolean =
    Implicits.RichJIterable(c).asScala.forall(this contains _.asInstanceOf[AnyRef])
  def isEmpty(): Boolean =
    underlying.isEmpty
  def size(): Int =
    underlying.size
  def toArray[S](array: Array[S]): Array[S] =
    underlying.toArray.map((x: T) => x.asInstanceOf[S])
  def toArray: Array[AnyRef] =
    underlying.toArray.map((x: T) => x.asInstanceOf[AnyRef])

  def add(o: T): Boolean =
    throw new UnsupportedOperationException
  def addAll(c: JCollection[_ <: T]): Boolean =
    throw new UnsupportedOperationException
  def clear(): Unit =
    throw new UnsupportedOperationException
  def remove(o: AnyRef): Boolean =
    throw new UnsupportedOperationException
  def removeAll(c: JCollection[_]): Boolean =
    throw new UnsupportedOperationException
  def retainAll(c: JCollection[_]): Boolean =
    throw new UnsupportedOperationException
}
