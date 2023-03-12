package com.example.composephilipplackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.composephilipplackner.ui.spacing
import com.example.composephilipplackner.ui.theme.ComposePhilippLacknerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePhilippLacknerTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.padding(MaterialTheme.spacing.medium)
                ) {
                    //content
                }
            }
        }
    }
}