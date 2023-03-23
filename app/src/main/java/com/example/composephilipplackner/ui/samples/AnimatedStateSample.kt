package com.example.composephilipplackner.ui.samples

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedStateSample() {
    Column(modifier = Modifier.fillMaxSize()) {

        var isRound by remember {
            mutableStateOf(false)
        }
        Button(onClick = {
            isRound = !isRound
        }) {
            Text(text = "Запуск")
        }
        val borderRadius by animateIntAsState(
            targetValue = if (isRound) 100 else 0,
            animationSpec = tween(durationMillis = 1000) //помимо tween есть sping или keyFrames
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(borderRadius))
                .background(Color.LightGray)
        )
    }
}

@Composable
fun AnimatedTransitionSample() {
    Column(modifier = Modifier.fillMaxSize()) {

        var isRound by remember {
            mutableStateOf(false)
        }
        Button(onClick = {
            isRound = !isRound
        }) {
            Text(text = "Запуск")
        }

        val transition = updateTransition(targetState = isRound, label = null)
        //для радиуса
        val borderRadius by transition.animateInt(
            transitionSpec = {
                tween(durationMillis = 2000)
            },
            label = "border",
            targetValueByState = {
                if (it) 100 else 0
            }
        )
        //для цвета
        val color by transition.animateColor(
            transitionSpec = {
                tween(durationMillis = 2000)
            },
            label = "color",
            targetValueByState = {
                if (it) Color.DarkGray else Color.Green
            }
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(borderRadius))
                .background(color)
        )
    }
}