package org.scala_tools.javautils

import java.util.HashSet
import scala.collection.jcl.{HashSet => SHashSet}
import Implicits.richHashSet

class RichHashSet[T](set: HashSet[T]) {
  def toScala: SHashSet[T] = new SHashSet(set)

  def foreach(fn: T => Unit): Unit = {
    val it = set.iterator
    while (it.hasNext) {
      fn(it.next)
    }
  }

  def filter(fn: T => Boolean): HashSet[T] = {
    val rv = new HashSet[T]
    val it = set.iterator
    while (it.hasNext) {
      val next = it.next
      if (fn(next)) {
        rv.add(next)
      }
    }
    rv
  }

  def map[S](fn: T => S): HashSet[S] = {
    val rv = new HashSet[S]
    val it = set.iterator
    while (it.hasNext) {
      rv.add(fn(it.next))
    }
    rv
  }

  def flatMap[S](fn: T => Iterable[S]): HashSet[S] = {
    val rv = new HashSet[S]
    val it = set.iterator
    while (it.hasNext) {
      fn(it.next).foreach(rv add _)
    }
    rv
  }
}
