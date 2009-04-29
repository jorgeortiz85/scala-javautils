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
import scala.collection.mutable.{Map => SMutableMap}
import scala.Function.untupled
import org.scala_tools.javautils.s2j.{SMapDictionaryWrapper, SMutableMapDictionaryWrapper}

class RichJDictionary[K, V](dictionary: Dictionary[K, V]) {
  def asScala: SMap[K, V] = dictionary match {
    case dw: SMapDictionaryWrapper[_, _] =>
      dw.asScala.asInstanceOf[SMap[K, V]]
    case _ => new JDictionaryWrapper[K, V] {
      type Wrapped = Dictionary[K, V]
      val underlying = dictionary
    }
  }

  def asScalaMutable: SMutableMap[K, V] = dictionary match {
    case mdw: SMutableMapDictionaryWrapper[_, _] =>
      mdw.asScala.asInstanceOf[SMutableMap[K, V]]
    case _ => new JMutableDictionaryWrapper[K, V] {
      type Wrapped = Dictionary[K, V]
      val underlying = dictionary
    }
  }

  def foreach(fn: Tuple2[K, V] => Unit): Unit =
    foreach(untupled(fn))

  def foreach(fn: (K, V) => Unit): Unit =
    Implicits.RichJEnumeration(dictionary.keys).foreach { key =>
      val value = dictionary.get(key)
      fn(key, value)
    }
}
