package org.roy.utils

object Utils {

  def getDataType[T](variable: T): String = variable.getClass.getSimpleName
}