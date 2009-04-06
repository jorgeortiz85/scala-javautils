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

import java.util.Collection
import scala.{Iterable => SIterable, Collection => SCollection}

abstract class RichCollection[T, C[U] <: Collection[U]](collection: C[T]) extends RichIterable(collection) {
  protected def build[S]: C[S]

  // TODO: Should perform in O(1) time
  override def toScala: SCollection[T] =
    Implicits.richJIterator(collection.iterator).toScala.toList

  def filter(fn: T => Boolean): C[T] = {
    val rv = build[T]
    this.foreach { e =>
      if (fn(e))
        rv.add(e)
    }
    rv
  }

  def map[S](fn: T => S): C[S] = {
    val rv = build[S]
    this.foreach { e =>
      rv.add(fn(e))
    }
    rv
  }

  def flatMap[S](fn: T => SIterable[S]): C[S] = {
    val rv = build[S]
    this.foreach { e =>
      fn(e).foreach(rv add _)
    }
    rv
  }
}
