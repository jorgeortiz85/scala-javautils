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

import java.util.{Iterator => JIterator, Enumeration => JEnumeration}
import scala.collection.jcl.MutableIterator.{Wrapper => JCLIteratorWrapper}
import org.scala_tools.javautils.j2s.wrappers._
import org.scala_tools.javautils.s2j.wrappers._

class RichSIterator[T](iterator: Iterator[T]) {
  def toJava: JIterator[T] = iterator match {
    case iw: JCLIteratorWrapper[_] =>
      iw.underlying.asInstanceOf[JIterator[T]]
    case iw: SIteratorWrapper[_] =>
      iw.toJava.asInstanceOf[JIterator[T]]
    case _ => new JIteratorWrapper[T] {
      type Wrapped = Iterator[T]
      protected val underlying = iterator
    }
  }
  def toJavaEnumeration: JEnumeration[T] = iterator match {
    case ew: SEnumerationWrapper[_] =>
      ew.toJava.asInstanceOf[JEnumeration[T]]
    case _ => new JEnumerationWrapper[T] {
      type Wrapped = Iterator[T]
      protected val underlying = iterator
    }
  }
}
