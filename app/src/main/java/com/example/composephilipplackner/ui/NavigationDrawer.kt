package com.example.composephilipplackner.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 60.sp)
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(it) }
                .padding(16.dp)
            ) {
                Icon(imageVector = it.icon, contentDescription = null)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = it.title, style = itemTextStyle, modifier = Modifier.weight(1f))
            }
        }
    }
}

data class MenuItem(
    val id: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun AppBar(
    onNavigationItemClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "App Example")
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = onNavigationItemClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}