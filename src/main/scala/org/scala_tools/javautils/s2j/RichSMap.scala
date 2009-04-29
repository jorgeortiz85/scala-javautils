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

import java.util.{Map => JMap, Dictionary => JDictionary}
import scala.collection.Map
import scala.collection.jcl.{MapWrapper => JCLMapWrapper}
import org.scala_tools.javautils.j2s.{JMapWrapper, JDictionaryWrapper}

class RichSMap[K, V](map: Map[K, V]) {
  def asJava: JMap[K, V] = map match {
    case mw: JCLMapWrapper[_, _] =>
      mw.underlying.asInstanceOf[JMap[K, V]]
    case mw: JMapWrapper[_, _] =>
      mw.asJava.asInstanceOf[JMap[K, V]]
    case _ => new SMapWrapper[K, V] {
      type Wrapped = Map[K, V]
      val underlying = map
    }
  }
  
  def asJavaDictionary: JDictionary[K, V] = map match {
    case dw: JDictionaryWrapper[_, _] =>
      dw.asJava.asInstanceOf[JDictionary[K, V]]
    case _ => new SMapDictionaryWrapper[K, V] {
      type Wrapped = Map[K, V]
      val underlying = map
    }
  }
}
