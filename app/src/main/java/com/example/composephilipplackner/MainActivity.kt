package com.example.composephilipplackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composephilipplackner.ui.theme.ComposePhilippLacknerTheme
import com.example.composephilipplackner.ui.theme.Shapes
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePhilippLacknerTheme {

                val sheetState = rememberBottomSheetState(
                    initialValue = BottomSheetValue.Collapsed,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                )
                val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
                val scope = rememberCoroutineScope()

                BottomSheetScaffold(
                    sheetContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Bottom sheet", fontSize = 60.sp)
                        }
                    },
                    sheetBackgroundColor = Color.Green,
                    scaffoldState = scaffoldState,
                    sheetPeekHeight = 0.dp
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = {
                            scope.launch {
                                if (sheetState.isCollapsed) {
                                    sheetState.expand()
                                } else {
                                    sheetState.collapse()
                                }
                            }
                        }) {
                            Text(text = "Показать нижнюю панель")
                        }
                    }
                }
            }
        }
    }
}