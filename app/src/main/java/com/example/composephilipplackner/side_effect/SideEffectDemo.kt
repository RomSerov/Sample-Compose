package com.example.composephilipplackner.side_effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

@Composable
fun SideEffectDemo(nonComposeCounter: Int) {
    SideEffect {
        println("Вызов после рекомпозиции")
    }
}