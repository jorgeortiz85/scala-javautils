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

import java.util.{Map => JMap, Set => JSet, Collection => JCollection}
import scala.collection.Map
import scala.Function.tupled

trait SMapWrapper[K, V] extends JMap[K, V] with SWrapper {
  type Wrapped <: Map[K, V]

  def containsKey(key: AnyRef): Boolean =
    // K is erased so this cast is safe
    underlying.contains(key.asInstanceOf[K])
  def containsValue(value: AnyRef): Boolean =
    underlying.exists(tupled((k, v) => v == value))
  def entrySet(): JSet[JMap.Entry[K, V]] =
    Implicits.RichSSet(
      Set() ++ 
        underlying.elements.map(
          tupled(Entry.apply _ : (K, V) => JMap.Entry[K, V]))
    ).asJava
  def get(key: AnyRef): V =
    // K is erased so this cast is safe
    underlying.apply(key.asInstanceOf[K])
  def isEmpty: Boolean =
    underlying.isEmpty
  def keySet: JSet[K] =
    Implicits.RichSSet(underlying.keySet).asJava
  def size: Int =
    underlying.size
  def values: JCollection[V] =
    Implicits.RichSCollection(underlying.values.collect).asJava

  def clear(): Unit =
    throw new UnsupportedOperationException
  def put(key: K, value: V): V =
    throw new UnsupportedOperationException
  def putAll(t: JMap[_ <: K, _ <: V]): Unit =
    throw new UnsupportedOperationException
  def remove(key: AnyRef): V =
    throw new UnsupportedOperationException
  
  private case class Entry(key: K, value: V) extends JMap.Entry[K, V] {
    def getKey(): K = key
    def getValue(): V = value
    def setValue(value: V) = throw new UnsupportedOperationException
  }
}
