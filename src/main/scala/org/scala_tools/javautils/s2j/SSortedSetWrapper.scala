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

import java.util.{Map => JMap, SortedSet => JSortedSet, Collection => JCollection, Comparator}
import org.scala_tools.javautils.j2s.RichJSortedSet
import scala.collection.SortedSet

trait SSortedSetWrapper[T] extends JSortedSet[T] with SSetWrapper[T] {
  type Wrapped <: SortedSet[T]

  override def first =
    underlying.firstKey
  override def last =
    underlying.lastKey
  override def comparator = new Comparator[T] {
    override def compare(a: T, b: T) = underlying.compare(a, b)
  }
  override def headSet(to: T) =
    new RichSSortedSet(underlying.until(to)).asJava
  override def subSet(from: T, to: T) =
    new RichSSortedSet(underlying.range(from, to)).asJava
  override def tailSet(from: T) =
    new RichSSortedSet(underlying.from(from)).asJava
}
