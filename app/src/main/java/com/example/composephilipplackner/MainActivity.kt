package com.example.composephilipplackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import com.example.composephilipplackner.ui.AppBar
import com.example.composephilipplackner.ui.DrawerBody
import com.example.composephilipplackner.ui.DrawerHeader
import com.example.composephilipplackner.ui.MenuItem
import com.example.composephilipplackner.ui.theme.ComposePhilippLacknerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePhilippLacknerTheme {

                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                             AppBar {
                                 scope.launch {
                                     scaffoldState.drawerState.open()
                                 }
                             }
                    },
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "home",
                                    title = "Home",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "settings",
                                    title = "Settings",
                                    icon = Icons.Default.Settings
                                ),
                                MenuItem(
                                    id = "help",
                                    title = "Help",
                                    icon = Icons.Default.Info
                                ),
                            ),
                            onItemClick = {

                            }
                        )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen
                ) {

                }
            }
        }
    }
}