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
import java.util.{Iterator => JIterator, List => JList, Collection => JCollection,
  ListIterator => JListIterator, Queue => JQueue}
import scala.collection.mutable.Buffer

trait SBufferQueueWrapper[T] extends JQueue[T] with SBufferWrapper[T] {
  // Queue methods
  override def add(elem: T): Boolean =
    modified(underlying += elem)
  override def offer(elem: T): Boolean =
    modified(underlying += elem)
  override def remove(): T =
    throwOr(underlying.remove(first))
  override def poll(): T =
    nullOr(underlying.remove(first))
  override def element(): T =
    throwOr(underlying.first)
  override def peek(): T =
    nullOr(underlying.first)

  // Helper methods
  private def first = 0
  private def last = underlying.size - 1

  // This helper method takes an action and returns the result of the action
  // if the collecton isn't empty. If the collection is empty, it returns null
  protected def nullOr[T](f: => T): T = {
    // TODO: Is this cast safe?? What to do for primitives?
    if (underlying.isEmpty)
      null.asInstanceOf[T]
    else
      f
  }
  
  // This helper method takes an action and returns the result of the action
  // if the collecton isn't empty. If the collection is empty, an exception
  // is thrown.
  private def throwOr[T](f: => T): T = {
    if (underlying.isEmpty)
      throw new NoSuchElementException
    else
      f
  }
}
