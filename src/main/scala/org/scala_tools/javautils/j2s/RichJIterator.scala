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

import java.util.Iterator
import scala.{Iterator => SIterator}
import org.scala_tools.javautils.j2s.wrappers.SIteratorWrapper
import org.scala_tools.javautils.s2j.wrappers.JIteratorWrapper

class RichJIterator[T](iterator: Iterator[T]) {
  def foreach(fn: T => Unit): Unit =
    while (iterator.hasNext)
      fn(iterator.next)

  def asScala: SIterator[T] = iterator match {
    case iw: JIteratorWrapper[_] =>
      iw.asScala.asInstanceOf[SIterator[T]]
    case _ => new SIteratorWrapper[T] {
      type Wrapped = Iterator[T]
      val underlying = iterator
    }
  }
}
