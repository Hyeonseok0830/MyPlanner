package com.myplanner.app.ui.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myplanner.app.R
import com.myplanner.app.ui.theme.MyPlannerTheme
import com.myplanner.app.util.textColor
import com.myplanner.app.util.textFieldBackground
import com.myplanner.app.viewmodel.CalendarViewModel


@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                HorizontalCalendar()
            }
        }
        Box(
            Modifier.padding(10.dp),
            Alignment.BottomEnd
        ) {

            var text by remember { mutableStateOf(TextFieldValue("")) }
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = textFieldBackground(),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = textColor()

                ),
                value = text,
                onValueChange = { newText -> text = newText },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.nav_main_desc)) },
                singleLine = true,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    MyPlannerTheme {
        CalendarScreen()
    }
}

