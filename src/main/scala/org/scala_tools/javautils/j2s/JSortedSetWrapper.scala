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
package org.scala_tools.javautils.j2s

import java.util.SortedSet
import scala.collection.{SortedSet => SSortedSet}

trait JSortedSetWrapper[T] extends SSortedSet[T] with JSetWrapper[T] {
  type Wrapped <: SortedSet[T]

  override def firstKey =
    underlying.first
  override def lastKey =
    underlying.last
  override def compare(a: T, b: T): Int = {
    val comparator = underlying.comparator
    if (comparator != null) {
      comparator.compare(a, b);
    } else {
      if (a.isInstanceOf[Comparable[_]]) {
        a.asInstanceOf[Comparable[T]].compareTo(b)
      } else {
        throw new IllegalStateException("No valid ordering available for members.");
      }
    }
  }

  override def rangeImpl(from: Option[T], to: Option[T]): SSortedSet[T] = (from, to) match {
    case (None,    None)    => this
    case (None,    Some(t)) => new RichJSortedSet(underlying.headSet(t)).asScala
    case (Some(f), None)    => new RichJSortedSet(underlying.tailSet(f)).asScala
    case (Some(f), Some(t)) => new RichJSortedSet(underlying.subSet(f, t)).asScala
  }
}
