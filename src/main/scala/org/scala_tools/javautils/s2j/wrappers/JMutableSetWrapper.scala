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
package org.scala_tools.javautils.s2j.wrappers

import java.util.{Map => JMap, Set => JSet, Collection => JCollection}
import scala.collection.mutable.Set

trait JMutableSetWrapper[T] extends JSet[T] with JSetWrapper[T] {
  type Wrapped <: Set[T]

  override def add(o: T): Boolean = 
    modified(underlying += o)
  override def addAll(c: JCollection[_ <: T]): Boolean =
    modified(Implicits.richJIterable(c).foreach(underlying += _))
  override def clear(): Unit =
    underlying.clear()
  override def remove(o: AnyRef): Boolean =
    modified(underlying -= o.asInstanceOf[T])
  override def removeAll(c: JCollection[_]): Boolean =
    modified(Implicits.richJIterable(c).foreach(underlying -= _.asInstanceOf[T]))
  override def retainAll(c: JCollection[_]): Boolean =
    modified(underlying.retain(c contains _))
}
