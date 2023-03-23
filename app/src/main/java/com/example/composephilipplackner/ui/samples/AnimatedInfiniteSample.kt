package com.example.composephilipplackner.ui.samples

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedInfiniteSample() {
    Column(modifier = Modifier.fillMaxSize()) {

        var isRound by remember {
            mutableStateOf(false)
        }
        Button(onClick = {
            isRound = !isRound
        }) {
            Text(text = "Запуск")
        }

        //для бесконечной анимации
        val transition = rememberInfiniteTransition()
        val color by transition.animateColor(
            initialValue = Color.Green,
            targetValue = Color.Blue,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000),
                repeatMode = RepeatMode.Reverse
            )
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(color)
        )
    }
}