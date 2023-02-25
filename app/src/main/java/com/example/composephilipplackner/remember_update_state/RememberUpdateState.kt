package com.example.composephilipplackner.remember_update_state

import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun RememberUpdate(onTimeout: () -> Unit) {

    val updateOnTimeout by rememberUpdatedState(newValue = onTimeout)

    LaunchedEffect(key1 = true) {
        delay(3000)
        updateOnTimeout()
    }
}