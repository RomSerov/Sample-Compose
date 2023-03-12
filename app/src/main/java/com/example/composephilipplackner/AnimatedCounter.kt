package com.example.composephilipplackner

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounter(
    count: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.body1
) {
    var oldCount by remember {
        mutableStateOf(count)
    }
    SideEffect {
        oldCount = count
    }
    Row(modifier = modifier) {
        val countStr = count.toString()
        val oldCountStr = oldCount.toString()
        for (i in countStr.indices) {
            val oldChar = oldCountStr.getOrNull(index = i)
            val newChar = countStr[i]
            val char = if (oldChar == newChar) {
                oldCountStr[i]
            } else {
                countStr[i]
            }
            AnimatedContent(
                targetState = char,
                transitionSpec = {
                    slideInVertically { it } with slideOutVertically { -it }
                }
            ) {
                Text(text = char.toString(), style = style, softWrap = false)
            }
        }
    }
}