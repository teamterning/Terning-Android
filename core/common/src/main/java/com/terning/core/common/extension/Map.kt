package com.terning.core.extension

fun<T> List<T>?.isListNotEmpty():Boolean = this.orEmpty().isNotEmpty()
