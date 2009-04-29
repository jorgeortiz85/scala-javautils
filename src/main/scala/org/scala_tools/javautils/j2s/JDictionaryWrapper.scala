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

import java.util.Dictionary
import scala.collection.{Map => SMap}

trait JDictionaryWrapper[K, V] extends SMap[K, V] with JWrapper {
  type Wrapped <: Dictionary[K, V]
  
  def get(key: K): Option[V] = {
    val value = underlying.get(key)
    if (value != null)
      Some(value)
    else
      None
  }
  def size =
    underlying.size
  def elements =
    Implicits.RichJEnumeration(underlying.keys).asScala.map { key =>
      (key, underlying.get(key))
    }
}
