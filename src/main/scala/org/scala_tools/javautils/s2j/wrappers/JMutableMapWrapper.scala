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

import java.util.{Map => JMap}
import scala.collection.mutable.Map

trait JMutableMapWrapper[K, V] extends JMap[K, V] with JMapWrapper[K, V] {
  type Wrapped <: Map[K, V]

  override def clear(): Unit =
    underlying.clear()
  override def put(key: K, value: V): V =
    // TODO: Is this cast safe?? What's the correct behavior for a Scala Map
    //   that uses primitives and is converted to a Java Map?
    underlying.put(key, value).getOrElse(null).asInstanceOf[V]
  override def putAll(t: JMap[_ <: K, _ <: V]): Unit =
    Implicits.richJMap(t).foreach { (key: K, value: V) =>
      underlying(key) = value
    }
  override def remove(key: AnyRef): V =
    // TODO: Are these casts safe??
    underlying.removeKey(key.asInstanceOf[K]).getOrElse(null).asInstanceOf[V]
  
  // TODO: Implement mutable Entries.
}
