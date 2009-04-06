package org.scala_tools.javautils

import java.util.ArrayList
import scala.collection.jcl.{ArrayList => SArrayList}
import Implicits.richArrayList

class RichArrayList[T](list: ArrayList[T]) {
  def toScala: SArrayList[T] = new SArrayList(list)

  def foreach(fn: T => Unit): Unit = {
    val it = list.iterator
    while (it.hasNext) {
      fn(it.next)
    }
  }

  def filter(fn: T => Boolean): ArrayList[T] = {
    val rv = new ArrayList[T]
    val it = list.iterator
    while (it.hasNext) {
      val next = it.next
      if (fn(next)) {
        rv.add(next)
      }
    }
    rv
  }

  def map[S](fn: T => S): ArrayList[S] = {
    val rv = new ArrayList[S]
    val it = list.iterator
    while (it.hasNext) {
      rv.add(fn(it.next))
    }
    rv
  }
  
  def flatMap[S](fn: T => Iterable[S]): ArrayList[S] = {
    val rv = new ArrayList[S]
    val it = list.iterator
    while (it.hasNext) {
      fn(it.next).foreach(rv add _)
    }
    rv
  }
}
