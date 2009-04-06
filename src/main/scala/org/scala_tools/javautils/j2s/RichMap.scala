package org.scala_tools.javautils.j2s

import java.util.Map
import scala.collection.jcl.{MapWrapper, Conversions}
import scala.collection.mutable.ArrayBuffer
import scala.Function.untupled

abstract class RichMap[K, V, C[X, Y] <: Map[X, Y]](map: C[K, V]) {
  protected def build[X, Y]: C[X, Y]
  def toScala: MapWrapper[K, V] = Conversions.convertMap(map)

  def foreach(fn: Tuple2[K, V] => Unit): Unit =
    foreach(untupled(fn))

  def foreach(fn: (K, V) => Unit): Unit =
    Implicits.richJIterator(map.entrySet.iterator).foreach { entry =>
      val (key, value) = (entry.getKey, entry.getValue)
      fn(key, value)
    }

  def filter(fn: Tuple2[K, V] => Boolean): C[K, V] =
    filter(untupled(fn))

  def filter(fn: (K, V) => Boolean): C[K, V] = {
    val rv = build[K, V]
    this.foreach { (key, value) =>
      if (fn(key, value))
        rv.put(key, value)
    }
    rv
  }

  def map[T](fn: Tuple2[K, V] => T): Iterable[T] =
    map(untupled(fn))

  def map[T](fn: (K, V) => T): Iterable[T] = {
    val rv = new ArrayBuffer[T]
    this.foreach { (key, value) =>
      rv += fn(key, value)
    }
    rv
  }

  def flatMap[T](fn: Tuple2[K, V] => Iterable[T]): Iterable[T] =
    flatMap(untupled(fn))

  def flatMap[T](fn: (K, V) => Iterable[T]): Iterable[T] = {
    val rv = new ArrayBuffer[T]
    this.foreach { (key, value) =>
      rv ++= fn(key, value)
    }
    rv
  }
}
