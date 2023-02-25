package com.example.composephilipplackner.produce_state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay

@Composable
fun ProduceStateDemo(countUpTo: Int): State<Int> {
    return produceState(initialValue = 0) {
        while (value < countUpTo) {
            delay(1000)
            value++
        }
    }
}

/* тоже самое что

return flow<Int> {
        var value = 0
        while (value < countUpTo) {
            delay(1000)
            value++
            emit(value)
        }
    }.collectAsState(initial = 0)

 */