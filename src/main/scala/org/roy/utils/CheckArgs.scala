
package org.roy.utils


object CheckArgs {

  def apply[T](args: T*): Boolean = checkArgs(args: _*)

  private def checkArgs[T](args: T*): Boolean = {
    args.forall(arg => if (arg == null) false else true)
  }
}