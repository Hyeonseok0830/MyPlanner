package com.myplanner.app.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myplanner.app.ui.theme.MyPlannerTheme
import com.myplanner.app.ui.theme.black
import com.myplanner.app.util.bottomSheetContainerColor
import com.myplanner.app.util.textColor
import com.myplanner.app.util.textFieldBackground
import com.myplanner.app.viewmodel.CalendarViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    Surface(
        color = MaterialTheme.colors.background,
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
        ModalBottomSheetScene()
    }

}


@ExperimentalMaterialApi
@Composable
fun ModalBottomSheetScene() {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded)
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    if (state.currentValue != ModalBottomSheetValue.Hidden) {
        DisposableEffect(Unit) {
            onDispose {
                focusManager.clearFocus()
            }
        }
    }
    ModalBottomSheetLayout(sheetState = state,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .align(Alignment.CenterHorizontally)
                    .background(bottomSheetContainerColor()), contentAlignment = Alignment.TopCenter
            ) {
                SheetContent(state, scope)
            }
        }, sheetBackgroundColor = Color.Transparent
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
                onClick = {
                    scope.launch {
                        state.show()
                    }
                }) {
                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null)
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
private fun SheetContent(
    state: ModalBottomSheetState, scope: CoroutineScope,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.heightIn(min = 100.dp, max = 500.dp)) {
        val date = viewModel.date.observeAsState()
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "${date.value}",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = black,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Center)
            )
            IconButton(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp),
                onClick = {
                    scope.launch {
                        state.hide()
                    }
                }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = black
                )
            }

        }
        Box(modifier = Modifier.fillMaxWidth()) {
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp, 60.dp, 10.dp),
                placeholder = { Text("일정을 입력하세요") },
                singleLine = true,
            )
            IconButton(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp),
                onClick = {
                    /*TODO ADD action*/
                }) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = null,
                    tint = black
                )
            }
        }
//        LazyColumn(
//            contentPadding = PaddingValues(8.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//
//        }
    }
}


@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    MyPlannerTheme {
        CalendarScreen()
    }
}

