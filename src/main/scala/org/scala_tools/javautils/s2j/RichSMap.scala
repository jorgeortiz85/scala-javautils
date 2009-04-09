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

import java.util.{Map => JMap}
import scala.collection.Map
import scala.collection.jcl.{MapWrapper => JCLMapWrapper}
import org.scala_tools.javautils.s2j.wrappers.JMapWrapper
import org.scala_tools.javautils.j2s.wrappers.SMapWrapper

class RichSMap[K, V](map: Map[K, V]) {
  def toJava: JMap[K, V] = map match {
    case mw: JCLMapWrapper[_, _] =>
      mw.underlying.asInstanceOf[JMap[K, V]]
    case mw: SMapWrapper[_, _] =>
      mw.toJava.asInstanceOf[JMap[K, V]]
    case _ => new JMapWrapper[K, V] {
      type Wrapped = Map[K, V]
      protected val underlying = map
    }
  }
}
