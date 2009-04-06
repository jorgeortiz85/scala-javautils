package org.scala_tools.javautils.j2s

import java.util.Enumeration
import scala.{Iterator => SIterator}

class RichEnumeration[T](enumeration: Enumeration[T]) {
  def foreach(fn: T => Unit): Unit =
    while (enumeration.hasMoreElements)
      fn(enumeration.nextElement)
  
  def toScala: SIterator[T] = new SIterator[T] {
    def hasNext = enumeration.hasMoreElements
    def next() = enumeration.nextElement
  }
}
