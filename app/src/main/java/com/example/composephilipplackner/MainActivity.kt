package com.example.composephilipplackner

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.composephilipplackner.ui.theme.ComposePhilippLacknerTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePhilippLacknerTheme {

                val permissionState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA
                    )
                )

                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(
                    key1 = lifecycleOwner,
                    effect = {
                        val observer = LifecycleEventObserver { _, event ->
                            if (event == Lifecycle.Event.ON_START) {
                                permissionState.launchMultiplePermissionRequest()
                            }
                        }
                        lifecycleOwner.lifecycle.addObserver(observer)

                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    }
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    permissionState.permissions.forEach {
                        when(it.permission) {
                            Manifest.permission.CAMERA -> {
                                when {
                                    it.hasPermission -> {
                                        Text(text = "Camera permission accepted")
                                    }
                                    it.shouldShowRationale -> {
                                        Text(text = "Camera permission is needed to access the camera")
                                    }
                                    !it.hasPermission && !it.shouldShowRationale -> {
                                        Text(text = "Camera permission was permanently denied")
                                    }
                                }
                            }
                            Manifest.permission.RECORD_AUDIO -> {
                                when {
                                    it.hasPermission -> {
                                        Text(text = "Record audio permission accepted")
                                    }
                                    it.shouldShowRationale -> {
                                        Text(text = "Record audio permission is needed to access the camera")
                                    }
                                    !it.hasPermission && !it.shouldShowRationale -> {
                                        Text(text = "Record audio permission was permanently denied")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}