/**
 * Copyright 2009 Kris Nuttycombe
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

import java.util.SortedSet
import scala.collection.{SortedSet => SSortedSet}
import org.scala_tools.javautils.s2j.SSortedSetWrapper

class RichJSortedSet[T](set: SortedSet[T]) {
  def asScala: SSortedSet[T] = set match {
    case sw: SSortedSetWrapper[_] =>
      sw.asScala.asInstanceOf[SSortedSet[T]]
    case _ => new JSortedSetWrapper[T] {
      type Wrapped = SortedSet[T]
      val underlying = set
    }
  }
}
