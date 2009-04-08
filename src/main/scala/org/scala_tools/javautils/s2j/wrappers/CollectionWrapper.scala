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
import java.util.{Iterator => JIterator, Collection => JCollection}

trait CollectionWrapper[T] extends IterableWrapper[T] with JCollection[T] {
  type Wrapped <: Collection[T]
  
  def add(o: T): Boolean =
    throw new UnsupportedOperationException
  def addAll(c: JCollection[_ <: T]): Boolean =
    j2s.Implicits.richJIterable(c).toScala.forall(this add _)
  def clear(): Unit =
    throw new UnsupportedOperationException
  def remove(o: AnyRef): Boolean =
    throw new UnsupportedOperationException
  def removeAll(c: JCollection[_]): Boolean =
    j2s.Implicits.richJIterable(c).toScala.forall(o => this.remove(o.asInstanceOf[AnyRef]))
  def retainAll(c: JCollection[_]): Boolean =
    throw new UnsupportedOperationException

  def contains(o: AnyRef): Boolean =
    underlying.exists(_ == o)
  def containsAll(c: JCollection[_]): Boolean =
    j2s.Implicits.richJIterable(c).toScala.forall(o => this.contains(o.asInstanceOf[AnyRef]))
  def isEmpty(): Boolean =
    underlying.isEmpty
  def size(): Int =
    underlying.size
  def toArray[S](array: Array[S]): Array[S] =
    underlying.toArray.map((x: T) => x.asInstanceOf[S])
  def toArray: Array[AnyRef] =
    underlying.toArray.map((x: T) => x.asInstanceOf[AnyRef])
}
