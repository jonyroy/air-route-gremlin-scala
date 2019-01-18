package org.roy.utils

object Utils {
    
    def getDataType[T](varialbe: T): String = varialbe.getClass.getSimpleName
}