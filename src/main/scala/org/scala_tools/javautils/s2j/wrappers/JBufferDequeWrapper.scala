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

import java.lang.{Iterable => JIterable}
import java.util.{Iterator => JIterator, List => JList, Collection => JCollection,
  ListIterator => JListIterator, Deque => JDeque}
import scala.collection.mutable.Buffer

trait JBufferDequeWrapper[T] extends JDeque[T] with JBufferWrapper[T] {
  override def addFirst(elem: T): Unit =
    elem +: underlying
  override def offerFirst(elem: T): Boolean =
    modified(elem +: underlying)
  override def addLast(elem: T): Unit =
    underlying += elem
  override def offerLast(elem: T): Boolean =
    modified(underlying += elem)
  override def removeFirst(): T =
    throwOr(underlying.remove(first))
  override def pollFirst(): T =
    nullOr(underlying.remove(first))
  override def removeLast(): T =
    throwOr(underlying.remove(last))
  override def pollLast(): T =
    nullOr(underlying.remove(last))
  override def getFirst(): T =
    throwOr(underlying(first))
  override def peekFirst(): T =
    nullOr(underlying(first))
  override def getLast(): T =
    throwOr(underlying(last))
  override def peekLast(): T =
    nullOr(underlying(last))
  
  // Queue methods
  override def add(elem: T): Boolean =
    modified(addLast(elem))
  override def offer(elem: T): Boolean =
    offerLast(elem)
  override def remove(): T =
    removeFirst()
  override def poll(): T =
    pollFirst()
  override def element(): T =
    getFirst()
  override def peek(): T =
    peekFirst()

  // Stack methods (along with peek() above)
  override def push(elem: T): Unit =
    addFirst(elem)
  override def pop(): T =
    removeFirst()

  override def removeLastOccurrence(other: Any): Boolean = modified {
    underlying.toList.zipWithIndex.filter {
      case (elem, _) => elem == other
    }.lastOption.foreach {
      case (_, i) => underlying.remove(i)
    }
  }
  override def removeFirstOccurrence(other: Any): Boolean = modified {
    underlying.toList.zipWithIndex.filter {
      case (elem, _) => elem == other
    }.headOption.foreach {
      case (_, i) => underlying.remove(i)
    }
  }
  override def descendingIterator(): JIterator[T] = new JIterator[T] {
    private var i = underlying.size
    override def hasNext() = i > 0
    override def next(): T = {
      if (i == 0)
        throw new NoSuchElementException

      i -= 1
      underlying(i)
    }
    override def remove() =
      throw new UnsupportedOperationException
  }

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
