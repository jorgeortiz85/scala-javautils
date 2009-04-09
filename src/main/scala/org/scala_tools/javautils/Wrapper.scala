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
package org.scala_tools.javautils

trait Wrapper {
  type Wrapped
  protected def underlying: Wrapped
  protected def wrapperType: String
  override def toString =
    wrapperType+"Wrapper("+underlying.toString+")"
  override def hashCode = underlying.hashCode
  override def equals(that: Any): Boolean = that match {
    case that: Wrapper => underlying equals that.underlying
    case _ => false
  }
}
