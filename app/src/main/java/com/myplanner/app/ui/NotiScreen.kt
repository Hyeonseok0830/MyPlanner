package com.myplanner.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotiScreen() {
    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center) {
            Text(text = "알림 화면", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
        }
    }
}
