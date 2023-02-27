package com.example.composephilipplackner.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composephilipplackner.R
import com.example.composephilipplackner.ui.theme.*
import kotlin.math.abs

@Composable
fun HomeScreen() {

    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column() {
            GreetingSection()

            ChipSection(chips = listOf("Сон", "Бессоница", "Депрессия"))

            CurrentMeditation()

            FeatureSection(
                features = listOf(
                    Feature(
                        title = "Медитация сна",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = "Советы для сна",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Feature(
                        title = "Ночной остров",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Feature(
                        title = "Успокаивающие звуки",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                )
            )
        }
        BottomMenu(
            items = listOf(
                BottomMenuContent("Начало", R.drawable.ic_home),
                BottomMenuContent("Медитация", R.drawable.ic_bubble),
                BottomMenuContent("Сон", R.drawable.ic_moon),
                BottomMenuContent("Музыка", R.drawable.ic_music),
                BottomMenuContent("Профиль", R.drawable.ic_profile),
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun GreetingSection(name: String = "Roman") {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Доброе утро, $name", style = MaterialTheme.typography.h2)
            Text(text = "Желаем хорошего дня!", style = MaterialTheme.typography.body1)
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(chips: List<String>) {

    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {
        items(chips.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(color: Color = LightRed) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column() {
            Text(text = "Заметки на каждый день", style = MaterialTheme.typography.h2)
            Text(
                text = "Медитация 3-10 мин.",
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)

@Composable
fun FeatureSection(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Функции",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(ratio = 1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        //получим ограничения
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //для волнистого фона
        val mediumColoredPoint1 = Offset(x = 0f, y = height * 0.3f)
        val mediumColoredPoint2 = Offset(x = width * 0.1f, y = height * 0.35f)
        val mediumColoredPoint3 = Offset(x = width * 0.4f, y = height * 0.05f)
        val mediumColoredPoint4 = Offset(x = width * 0.75f, y = height * 0.7f)
        val mediumColoredPoint5 = Offset(x = width * 1.4f, y = -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(x = mediumColoredPoint1.x, y = mediumColoredPoint1.y)
            quadraticBezierTo(
                x1 = mediumColoredPoint1.x,
                y1 = mediumColoredPoint1.y,
                x2 = abs(mediumColoredPoint1.x + mediumColoredPoint2.x) / 2f,
                y2 = abs(mediumColoredPoint1.y + mediumColoredPoint2.y) / 2f
            )
            quadraticBezierTo(
                x1 = mediumColoredPoint2.x,
                y1 = mediumColoredPoint2.y,
                x2 = abs(mediumColoredPoint2.x + mediumColoredPoint3.x) / 2f,
                y2 = abs(mediumColoredPoint2.y + mediumColoredPoint3.y) / 2f
            )
            quadraticBezierTo(
                x1 = mediumColoredPoint3.x,
                y1 = mediumColoredPoint3.y,
                x2 = abs(mediumColoredPoint3.x + mediumColoredPoint4.x) / 2f,
                y2 = abs(mediumColoredPoint3.y + mediumColoredPoint4.y) / 2f
            )
            quadraticBezierTo(
                x1 = mediumColoredPoint4.x,
                y1 = mediumColoredPoint4.y,
                x2 = abs(mediumColoredPoint4.x + mediumColoredPoint5.x) / 2f,
                y2 = abs(mediumColoredPoint4.y + mediumColoredPoint5.y) / 2f
            )
            lineTo(x = width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(x = -100f, height.toFloat() + 100f)
            close()
        }

        val lightColoredPoint1 = Offset(x = 0f, y = height * 0.35f)
        val lightColoredPoint2 = Offset(x = width * 0.1f, y = height * 0.4f)
        val lightColoredPoint3 = Offset(x = width * 0.3f, y = height * 0.35f)
        val lightColoredPoint4 = Offset(x = width * 0.65f, y = height.toFloat())
        val lightColoredPoint5 = Offset(x = width * 1.4f, y = -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(x = lightColoredPoint1.x, y = lightColoredPoint1.y)
            quadraticBezierTo(
                x1 = lightColoredPoint1.x,
                y1 = lightColoredPoint1.y,
                x2 = abs(lightColoredPoint1.x + lightColoredPoint2.x) / 2f,
                y2 = abs(lightColoredPoint1.y + lightColoredPoint2.y) / 2f
            )
            quadraticBezierTo(
                x1 = lightColoredPoint2.x,
                y1 = lightColoredPoint2.y,
                x2 = abs(lightColoredPoint2.x + lightColoredPoint3.x) / 2f,
                y2 = abs(lightColoredPoint2.y + lightColoredPoint3.y) / 2f
            )
            quadraticBezierTo(
                x1 = lightColoredPoint3.x,
                y1 = lightColoredPoint3.y,
                x2 = abs(lightColoredPoint3.x + lightColoredPoint4.x) / 2f,
                y2 = abs(lightColoredPoint3.y + lightColoredPoint4.y) / 2f
            )
            quadraticBezierTo(
                x1 = lightColoredPoint4.x,
                y1 = lightColoredPoint4.y,
                x2 = abs(lightColoredPoint4.x + lightColoredPoint5.x) / 2f,
                y2 = abs(lightColoredPoint4.y + lightColoredPoint5.y) / 2f
            )
            lineTo(x = width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(x = -100f, height.toFloat() + 100f)
            close()
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumColoredPath, color = feature.mediumColor)
            drawPath(path = lightColoredPath, color = feature.lightColor)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}

data class BottomMenuContent(val title: String, @DrawableRes val iconId: Int)

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeColor: Color = ButtonBlue,
    activeText: Color = Color.White,
    inactiveText: Color = AquaBlue,
    initSelectedIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initSelectedIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeColor = activeColor,
                activeText = activeText,
                inactiveText = inactiveText,
                onItemClick = { selectedItemIndex = index })
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeColor: Color = ButtonBlue,
    activeText: Color = Color.White,
    inactiveText: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onItemClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = null,
                tint = if (isSelected) activeText else inactiveText,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeText else inactiveText
        )
    }
}