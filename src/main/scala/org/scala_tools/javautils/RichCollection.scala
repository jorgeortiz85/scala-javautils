package org.scala_tools.javautils

import java.lang.Iterable
import java.util.Collection
import scala.{Iterable => SIterable}
import Implicits.richIterable

abstract class RichCollection[T, C[U] <: Collection[U]](collection: C[T]) extends RichIterable(collection) {
  def build[S]: C[S]
  
  def filter(fn: T => Boolean): C[T] = {
    val rv = build[T]
    for (e <- collection) {
      if (fn(e))
        rv.add(e)
    }
    rv
  }
  
  def map[S](fn: T => S): C[S] = {
    val rv = build[S]
    for (e <- collection) {
      rv.add(fn(e))
    }
    rv
  }
  
  def flatMap[S](fn: T => SIterable[S]): C[S] = {
    val rv = build[S]
    for (e <- collection) {
      fn(e).foreach(rv add _)
    }
    rv
  }
}
