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
package org.scala_tools.javautils.s2j

import java.util.{List => JList}
import scala.collection.jcl.{BufferWrapper => JCLBufferWrapper}
import org.scala_tools.javautils.s2j.wrappers.JRandomAccessSeqMutableWrapper
import org.scala_tools.javautils.j2s.wrappers.SMutableListWrapper

class RichSRandomAccessSeqMutable[T](seq: RandomAccessSeq.Mutable[T]) {
  def asJava: JList[T] = seq match {
    case bw: JCLBufferWrapper[_] =>
      bw.underlying.asInstanceOf[JList[T]]
    case mlw: SMutableListWrapper[_] =>
      mlw.asJava.asInstanceOf[JList[T]]
    case _ => new JRandomAccessSeqMutableWrapper[T] {
      type Wrapped = RandomAccessSeq.Mutable[T]
      val underlying = seq
    }
  }
}
