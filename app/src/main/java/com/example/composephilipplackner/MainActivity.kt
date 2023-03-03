package com.example.composephilipplackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composephilipplackner.ui.theme.ComposePhilippLacknerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePhilippLacknerTheme {
                Surface(
                    color = Color(0xFF101010),
                    modifier = Modifier.padding(15.dp)
                ) {
                    DropDown(
                        text = "Волк волку волк!",
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Text(
                            text = "Оппаньки...",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .background(Color.Green)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DropDown(
    text: String,
    modifier: Modifier = Modifier,
    initOpened: Boolean = false,
    content: @Composable () -> Unit
) {
    var isOpened by remember {
        mutableStateOf(initOpened)
    }
    val alpha = animateFloatAsState(
        targetValue = if (isOpened) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    val rotateX = animateFloatAsState(
        targetValue = if (isOpened) 0f else -90f,
        animationSpec = tween(durationMillis = 300)
    )
    Column(modifier = modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .clickable { isOpened = !isOpened }
                    .scale(scaleX = 1f, scaleY = if (isOpened) -1f else 1f)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    //сдвинуть ось к верху box
                    transformOrigin = TransformOrigin(pivotFractionX = 0.5f, pivotFractionY = 0f)
                    rotationX = rotateX.value
                }
                .alpha(alpha = alpha.value)
        ) {
            content()
        }
    }
}