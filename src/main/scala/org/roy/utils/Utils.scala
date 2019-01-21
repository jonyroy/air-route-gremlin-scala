package org.roy.utils

object Utils {

  def getDataType[T](variable: T): String = variable.getClass.getSimpleName

  def isStringNonEmpty(elems: String*): Boolean = elems.forall(elem => IsNotNull(elem) && elem.nonEmpty)

  def getCurrentTimeMS: Long = java.lang.System.currentTimeMillis()
}