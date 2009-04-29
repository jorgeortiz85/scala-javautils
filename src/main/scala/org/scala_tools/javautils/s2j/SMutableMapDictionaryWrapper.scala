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
import scala.collection.mutable.Map

trait SMutableMapDictionaryWrapper[K, V] extends JDictionary[K, V]
    with SMapDictionaryWrapper[K, V] {
  type Wrapped <: Map[K, V]

  override def put(key: K, value: V): V =
    // TODO: Is this cast safe?? What's the correct behavior for a Scala Map
    //   that uses primitives and is converted to a Java Map?
    underlying.put(key, value).getOrElse(null).asInstanceOf[V]
  override def remove(key: AnyRef): V =
    // TODO: Are these casts safe??
    underlying.removeKey(key.asInstanceOf[K]).getOrElse(null).asInstanceOf[V]
}
