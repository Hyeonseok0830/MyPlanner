package com.myplanner.app.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.myplanner.app.ui.theme.black
import com.myplanner.app.ui.theme.blue01
import com.myplanner.app.ui.theme.gray
import com.myplanner.app.ui.theme.pink
import com.myplanner.app.ui.theme.transparent
import com.myplanner.app.ui.theme.white
import com.myplanner.app.ui.theme.white01

@Composable
fun textColor(): Color = if(isSystemInDarkTheme()) white else black
@Composable
fun textFieldBackground(): Color = if (isSystemInDarkTheme()) pink else gray


@Composable
fun calendarSelectTextColor(): Color = if(isSystemInDarkTheme()) black else black
@Composable
fun calendarSelectBackground(): Color = if (isSystemInDarkTheme()) white01 else blue01
@Composable
fun calendarDefaultBackground(): Color = if (isSystemInDarkTheme()) transparent else white
