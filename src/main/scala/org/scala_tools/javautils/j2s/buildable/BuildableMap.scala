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
package org.scala_tools.javautils.j2s.buildable

import java.util.Map
import scala.collection.jcl.{Conversions, MapWrapper}
import scala.collection.mutable.ArrayBuffer
import scala.Function.untupled

abstract class BuildableMap[K, V, C[X, Y] <: Map[X, Y]](map: C[K, V]) {
  protected def build[X, Y]: C[X, Y]

  def filter(fn: Tuple2[K, V] => Boolean): C[K, V] =
    filter(untupled(fn))

  def filter(fn: (K, V) => Boolean): C[K, V] = {
    val rv = build[K, V]
    Implicits.richJMap(map).foreach { (key, value) =>
      if (fn(key, value))
        rv.put(key, value)
    }
    rv
  }

  def map[T](fn: Tuple2[K, V] => T): Iterable[T] =
    map(untupled(fn))

  def map[T](fn: (K, V) => T): Iterable[T] = {
    val rv = new ArrayBuffer[T]
    Implicits.richJMap(map).foreach { (key, value) =>
      rv += fn(key, value)
    }
    rv
  }

  def flatMap[T](fn: Tuple2[K, V] => Iterable[T]): Iterable[T] =
    flatMap(untupled(fn))

  def flatMap[T](fn: (K, V) => Iterable[T]): Iterable[T] = {
    val rv = new ArrayBuffer[T]
    Implicits.richJMap(map).foreach { (key, value) =>
      rv ++= fn(key, value)
    }
    rv
  }
}
