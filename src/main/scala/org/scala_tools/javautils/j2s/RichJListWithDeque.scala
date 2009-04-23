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

import java.util.{List => JList, Deque => JDeque}
import scala.collection.mutable.Buffer
import org.scala_tools.javautils.j2s.wrappers.SListWithDequeWrapper
import org.scala_tools.javautils.s2j.wrappers.JBufferDequeWrapper

class RichJListWithDeque[T](lwd: JList[T] with JDeque[T]) {
  def asScala: Buffer[T] = lwd match {
    case bdw: JBufferDequeWrapper[_] =>
      bdw.asScala.asInstanceOf[Buffer[T]]
    case _ => new SListWithDequeWrapper[T] {
      type Wrapped = JList[T] with JDeque[T]
      val underlying = lwd
    }
  }
}
