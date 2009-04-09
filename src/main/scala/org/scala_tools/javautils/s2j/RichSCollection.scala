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

import java.util.{Collection => JCollection}
import scala.collection.jcl.{IterableWrapper => JCLIterableWrapper}
import org.scala_tools.javautils.s2j.wrappers.JCollectionWrapper
import org.scala_tools.javautils.j2s.wrappers.SCollectionWrapper

class RichSCollection[T](collection: Collection[T]) {
  def toJava: JCollection[T] = collection match {
    case iw: JCLIterableWrapper[_] =>
      iw.underlying.asInstanceOf[JCollection[T]]
    case iw: SCollectionWrapper[_] =>
      iw.toJava.asInstanceOf[JCollection[T]]
    case _ => new JCollectionWrapper[T] {
      type Wrapped = Collection[T]
      protected val underlying = collection
    }
  }
}
