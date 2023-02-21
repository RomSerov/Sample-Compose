package com.example.composephilipplackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composephilipplackner.ui.theme.ComposePhilippLacknerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()

            var textFieldState by remember {
                mutableStateOf("")
            }

            val scope = rememberCoroutineScope()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = textFieldState,
                        label = {
                                Text(text = "Введите имя")
                        },
                        onValueChange = {
                            textFieldState = it
                        },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Привет, $textFieldState")
                            }
                        }) {
                        Text(text = "Нажми, чтоб поздороваться")
                    }
                }
            }
        }
    }
}