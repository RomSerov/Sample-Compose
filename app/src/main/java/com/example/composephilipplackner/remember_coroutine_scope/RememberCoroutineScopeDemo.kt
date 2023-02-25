package com.example.composephilipplackner.remember_coroutine_scope

import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RememberCoroutine() {
    
    val scope = rememberCoroutineScope()
    
    Button(onClick = {
        scope.launch {
            delay(1000)
            println("Hello")
        }
    }) {

    }
}