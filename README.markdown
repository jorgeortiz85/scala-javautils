# scala-javautils

Utilities for dealing with Java and Scala collections.

**NOTE: For Scala 2.8.x, this library has been superseded by [scalaj-collection](http://github.com/scalaj/scalaj-collection)**

## Usage

Turn any Scala collection into the corresponding Java collection:

    import org.scala_tools.javautils.Imports._

    List(1, 2, 3).asJava
    // returns java.util.List[Int]

    Map(1 -> "a", 2 -> "b", 3 -> "c").asJava
    // returns java.util.Map[Int, String]

    Set(1, 2, 3).asJava
    // returns java.util.Set[Int]

Turn any Java collection into the corresponding Scala collection:

    val list = new java.util.ArrayList[Int]
    list.add(1)
    list.add(2)
    list.add(3)
    list.asScala
    // returns scala.Seq[Int]

For the full list of available conversions, please see the NOTES file.

You can also use `foreach` on any Java collection:

    list.foreach(print)
    // prints "123"

The `map`, `filter`, and `flatMap` methods are still pending.

## Motivation

The Scala standard library includes a set of utilities for working with Java
collection in Scala (scala.collection.jcl). It is deficient in several ways:

1. It neglects several important Java interfaces, most notably: Enumeration,
   Iterator, Iterable, and Collection.

2. There is no way to convert from an arbitrary Scala collection to a Java
   collection. It is suitable for those wishing to use Java collections via
   Scala collection interfaces, but is unsuitable for those wishing to use
   Scala collections with legacy Java libraries that expect Java collections.

3. It is reckless in its use of implicits. The implicit conversions in
   scala.collection.jcl.Conversions are dangerous and should not be trusted.
   They "massage" types in ways that can be unpredictable for users not
   intimately familiar with scala.collections.jcl.

## Known Issues

There are no tests.

Iterators on mutable collections aren't themselves mutable.

None of the wrappers make any attempt at thread-safety, even when the underlying
collection is thread-safe.

Classes inheriting from Wrapper probably lose symmetry for "equals".

Wrappers may throw the wrong exception (i.e., exception thrown by the wrapped
collection are not anticipated by the wrapper interface).
