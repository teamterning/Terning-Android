package com.terning.core.common.extension

fun<T> List<T>?.isListNotEmpty():Boolean = this.orEmpty().isNotEmpty()
