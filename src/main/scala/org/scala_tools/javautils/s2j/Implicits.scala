package org.scala_tools.javautils.s2j

object Implicits extends Implicits

trait Implicits {
  implicit def richSIterator[T](iterator: Iterator[T]) = new RichIterator(iterator)
}
