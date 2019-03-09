package com.gumichan01.fkalculus.util

sealed class Option<out T>
object None : Option<Nothing>()
data class Some<out T>(val t: T): Option<T>()