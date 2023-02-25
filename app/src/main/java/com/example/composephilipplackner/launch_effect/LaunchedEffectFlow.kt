package com.example.composephilipplackner.launch_effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LaunchedEffectFlow(viewModel: LaunchedEffectViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collect {
            when(it) {
                is LaunchedEffectViewModel.ScreenEvents.Navigate -> TODO()
                is LaunchedEffectViewModel.ScreenEvents.ShowSnackBar -> TODO()
            }
        }
    }
}