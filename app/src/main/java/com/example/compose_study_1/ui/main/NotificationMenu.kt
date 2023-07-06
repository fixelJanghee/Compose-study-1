package com.example.compose_study_1.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotificationMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit
) {
    val items = listOf(
        "드롭 다운 메뉴 0",
        "드롭 다운 메뉴 1",
        "드롭 다운 메뉴 2",
        "드롭 다운 메뉴 3",
        "드롭 다운 메뉴 4",
        "드롭 다운 메뉴 5"
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            items.forEachIndexed { index, element ->
                when (index) {
                    items.lastIndex -> {
                        Text(
                            text = element,
                            modifier = Modifier
                                .padding(vertical = 9.dp, horizontal = 16.dp)
                                .fillMaxWidth(),
                            style = TextStyle(
                                color = Color.Blue,
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        )
                    }

                    else -> {
                        Text(
                            text = element,
                            modifier = Modifier
                                .padding(vertical = 9.dp, horizontal = 16.dp)
                                .fillMaxWidth(),
                            style = TextStyle(
                                color = Color.Blue,
                                fontSize = 11.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        )
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = Color.DarkGray
                        )

                    }
                }
            }
        }
    }
}
