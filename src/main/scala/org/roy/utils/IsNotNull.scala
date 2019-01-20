
package org.roy.utils


object IsNotNull {

  def apply[T](args: T*): Boolean = isNotNull(args: _*)

  private def isNotNull[T](args: T*): Boolean = {
    args.forall(arg => if (arg == null) false else true)
  }
}