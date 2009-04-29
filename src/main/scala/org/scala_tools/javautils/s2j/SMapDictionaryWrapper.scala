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

import java.util.{Dictionary => JDictionary, Enumeration => JEnumeration}
import scala.collection.Map
import scala.Function.tupled

trait SMapDictionaryWrapper[K, V] extends JDictionary[K, V] with SWrapper {
  type Wrapped <: Map[K, V]

  override def get(key: AnyRef): V =
    // K is erased so this cast is safe
    underlying.apply(key.asInstanceOf[K])
  
  override def isEmpty(): Boolean =
    underlying.isEmpty
  override def size(): Int =
    underlying.size

  override def elements(): JEnumeration[V] =
    Implicits.RichSIterator(underlying.values).asJavaEnumeration
  override def keys(): JEnumeration[K] =
    Implicits.RichSIterator(underlying.keys).asJavaEnumeration

  override def put(key: K, value: V): V =
    throw new UnsupportedOperationException
  override def remove(key: AnyRef): V =
    throw new UnsupportedOperationException
}
