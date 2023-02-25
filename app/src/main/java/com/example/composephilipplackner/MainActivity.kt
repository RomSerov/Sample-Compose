package com.example.composephilipplackner

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composephilipplackner.ui.theme.ComposePhilippLacknerTheme
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePhilippLacknerTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF101010)),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .border(width = 1.dp, color = Color.Green, RoundedCornerShape(10.dp))
                            .padding(30.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var volume by remember {
                            mutableStateOf(0f)
                        }
                        val barCount = 20
                        
                        MusicKnob(
                            modifier = Modifier.size(100.dp)
                        ) {
                            volume = it
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        VolumeBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp),
                            activeBar = (barCount * volume).roundToInt(),
                            barCount = barCount
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBar: Int = 0,
    barCount: Int = 10
) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount)
        }

        Canvas(modifier = modifier) {
            for (i in 0 until barCount) {
                drawRoundRect(
                    color = if (i in 0..activeBar) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2f + barCount / 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitAngle: Float = 25f,
    onValueChange: (Float) -> Unit,
) {
    //состояние по вращению
    var rotation by remember {
        mutableStateOf(limitAngle)
    }
    var touchX by remember {
        mutableStateOf(0f)
    }
    var touchY by remember {
        mutableStateOf(0f)
    }
    var centerX by remember {
        mutableStateOf(0f)
    }
    var centerY by remember {
        mutableStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = null,
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                //вычислим центральное положение картинки
                val windowBounds = it.boundsInWindow()
                centerX = windowBounds.size.width / 2f
                centerY = windowBounds.size.height / 2f
            } //вычислим положение касания
            .pointerInteropFilter { event ->
                touchX = event.x
                touchY = event.y
                //также нужно перевести из радианов в градусы
                val angle =
                    -atan2(y = centerX - touchY, x = centerY - touchY) * (180f / PI).toFloat()

                when (event.action) {
                    MotionEvent.ACTION_MOVE -> {
                        //проверим начальный угол, так как картинку захотели сделать с начальным сдвигом
                        if (angle !in -limitAngle..limitAngle) {
                            val fixedAngle = if (angle in -180f..-limitAngle) {
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitAngle) / (360f - 2 * limitAngle)
                            onValueChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )
}