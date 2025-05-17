package com.terning.core.designsystem.extension

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flow

fun <T, K> Flow<T>.groupBy(getKey: (T) -> K): Flow<Pair<K, Flow<T>>> = flow {
    val storage = mutableMapOf<K, SendChannel<T>>()
    try {
        collect { t ->
            val key = getKey(t)
            val channel = storage.getOrPut(key) {
                Channel<T>(capacity = Channel.BUFFERED).also {
                    emit(key to it.consumeAsFlow())
                }
            }
            channel.send(t)
        }
    } finally {
        storage.values.forEach { it.close() }
    }
}