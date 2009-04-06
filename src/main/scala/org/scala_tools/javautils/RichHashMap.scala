package org.scala_tools.javautils

import java.util.{HashMap, Map}
import scala.collection.jcl.{HashMap => SHashMap}
import scala.collection.mutable.ArrayBuffer
import scala.Function.untupled
import Implicits.richHashMap

class RichHashMap[K, V](map: HashMap[K, V]) {
  def toScala: SHashMap[K, V] = new SHashMap(map)

  def foreach(fn: Tuple2[K, V] => Unit): Unit =
    foreach(untupled(fn))

  def foreach(fn: (K, V) => Unit): Unit = {
    val it = map.entrySet.iterator
    while (it.hasNext) {
      val next = it.next
      val key = next.getKey
      val value = next.getValue
      fn(key, value)
    }
  }
  
  def filter(fn: Tuple2[K, V] => Boolean): HashMap[K, V] =
    filter(untupled(fn))

  def filter(fn: (K, V) => Boolean): HashMap[K, V] = {
    val rv = new HashMap[K, V]
    val it = map.entrySet.iterator
    while (it.hasNext) {
      val next = it.next
      val key = next.getKey
      val value = next.getValue
      if (fn(key, value)) {
        rv.put(key, value)
      }
    }
    rv
  }

  def map[T](fn: Tuple2[K, V] => T): Iterable[T] =
    map(untupled(fn))

  def map[T](fn: (K, V) => T): Iterable[T] = {
    val rv = new ArrayBuffer[T]
    val it = map.entrySet.iterator
    while (it.hasNext) {
      val next = it.next
      val key = next.getKey
      val value = next.getValue
      rv += fn(key, value)
    }
    rv
  }
  
  def flatMap[T](fn: Tuple2[K, V] => Iterable[T]): Iterable[T] =
    flatMap(untupled(fn))

  def flatMap[T](fn: (K, V) => Iterable[T]): Iterable[T] = {
    val rv = new ArrayBuffer[T]
    val it = map.entrySet.iterator
    while (it.hasNext) {
      val next = it.next
      val key = next.getKey
      val value = next.getValue
      rv ++= fn(key, value)
    }
    rv
  }
}
