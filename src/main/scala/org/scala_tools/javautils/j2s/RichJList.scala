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
package org.scala_tools.javautils.j2s

import java.util.List
import org.scala_tools.javautils.j2s.wrappers.{SListWrapper, SMutableListWrapper}
import org.scala_tools.javautils.s2j.wrappers.{JSeqWrapper, JRandomAccessSeqMutableWrapper}

class RichJList[T](list: List[T]) {
  def asScala: Seq[T] = list match {
    case sw: JSeqWrapper[_] =>
      sw.asScala.asInstanceOf[Seq[T]]
    case _ => new SListWrapper[T] {
      type Wrapped = List[T]
      val underlying = list
    }
  }
  
  def asScalaMutable: RandomAccessSeq.Mutable[T] = list match {
    case msw: JRandomAccessSeqMutableWrapper[_] =>
      msw.asScala.asInstanceOf[RandomAccessSeq.Mutable[T]]
    case _ => new SMutableListWrapper[T] {
      type Wrapped = List[T]
      val underlying = list
    }
  }
}
