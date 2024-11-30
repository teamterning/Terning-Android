package com.terning.core.designsystem.extension

fun<T> List<T>?.isListNotEmpty():Boolean = this.orEmpty().isNotEmpty()
