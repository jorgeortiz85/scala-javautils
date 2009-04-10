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

import java.lang.{Iterable => JIterable}
import java.util.{Collection => JCollection}
import scala.collection.jcl.{IterableWrapper => JCLIterableWrapper}
import org.scala_tools.javautils.s2j.wrappers.JIterableWrapper
import org.scala_tools.javautils.j2s.wrappers.SIterableWrapper

class RichSIterable[T](iterable: Iterable[T]) {
  def asJava: JIterable[T] = iterable match {
    case iw: JCLIterableWrapper[_] =>
      iw.underlying.asInstanceOf[JCollection[T]]
    case iw: SIterableWrapper[_] =>
      iw.asJava.asInstanceOf[JIterable[T]]
    case _ => new JIterableWrapper[T] {
      type Wrapped = Iterable[T]
      val underlying = iterable
    }
  }
}
