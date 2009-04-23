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
package org.scala_tools.javautils.j2s.wrappers

import java.util.{List => JList, Deque => JDeque}
import scala.collection.mutable.Buffer

// TODO: Better implementation-specific wrappers
trait SListWithDequeWrapper[T] extends Buffer[T] with SCollectionWrapper[T] {
  type Wrapped <: JList[T] with JDeque[T]
  
  override def clear(): Unit = underlying.clear()

  override def remove(n: Int): T = {
    if ((n < 0) || (n >= underlying.size))
      throw new IndexOutOfBoundsException("cannot remove element at " + n)

    val it = underlying.iterator
    // cycle through iterator until nth element, keep it as rv
    var rv = (0 to n).map(_ => it.next).last
    // remove nth element
    it.remove
    // return nth elemtn
    rv
  }

  override def update(n: Int, elem: T): Unit = {
    if ((n < 0) || (n >= underlying.size))
      throw new IndexOutOfBoundsException("cannot update element at " + n)
    
    // save first n-1 elements in stack
    val stack = (0 until n).map(_ => underlying.removeFirst()).reverse
    // discard nth element
    underlying.removeFirst()
    // update nth element
    underlying.addFirst(elem)
    // restore first n-1 elements
    stack.foreach(underlying addFirst _)
  }
  
  def insertAll(n: Int, iter: Iterable[T]): Unit = {
    if ((n < 0) || (n > underlying.size))
      throw new IndexOutOfBoundsException("cannot insert element at " + n)
    
    // save last size-n elements in stack
    val stack = (n until underlying.size).map(_ => underlying.removeLast()).reverse
    // add iter to end of buffer
    iter.foreach(underlying addLast _)
    // restore saved stack to end of buffer
    stack.foreach(underlying addLast _)
  }
  
  // TODO: Implement
  def readOnly: Seq[T] = null
  
  def +:(elem: T): Buffer[T] = {
    underlying.addFirst(elem)
    this
  }
  
  def +=(elem: T): Unit = {
    underlying.addLast(elem)
  }
  
  def length: Int = underlying.size
  
  def apply(n: Int): T = {
    val it = underlying.iterator
    (0 to n).map(_ => it.next).last
  }
}
