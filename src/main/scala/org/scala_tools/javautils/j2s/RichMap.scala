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

import java.util.Map
import scala.collection.jcl.{Conversions, MapWrapper}
import scala.collection.mutable.ArrayBuffer
import scala.Function.untupled

class RichMap[K, V](map: Map[K, V]) {
  def toScala: MapWrapper[K, V] = Conversions.convertMap(map)

  def foreach(fn: Tuple2[K, V] => Unit): Unit =
    foreach(untupled(fn))

  def foreach(fn: (K, V) => Unit): Unit =
    Implicits.richJIterator(map.entrySet.iterator).foreach { entry =>
      val (key, value) = (entry.getKey, entry.getValue)
      fn(key, value)
    }
}
