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

import java.util.{List => JList, Deque => JDeque}
import scala.collection.mutable.Buffer

trait JListWithDequeWrapper[T] extends Buffer[T] with JCollectionWrapper[T] {
  type Wrapped <: JList[T] with JDeque[T]
  
  override def clear(): Unit =
    underlying.clear()

  override def remove(n: Int): T =
    underlying.remove(n)

  override def update(n: Int, elem: T): Unit =
    underlying.set(n, elem)

  def insertAll(n: Int, iter: Iterable[T]): Unit =
    underlying.addAll(n, Implicits.RichSCollection(iter.toList).asJava)

  // TODO: Implement
  def readOnly: Seq[T] =
    null
  
  def +:(elem: T): Buffer[T] =
    returnThis(underlying.addFirst(elem))

  def +=(elem: T): Unit =
    underlying.addLast(elem)
  
  def length: Int =
    underlying.size
  
  def apply(n: Int): T =
    underlying.get(n)

  protected def returnThis[T](t: T): this.type =
    this
}
