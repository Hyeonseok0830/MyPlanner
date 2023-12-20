package com.myplanner.app.ui.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import com.myplanner.app.ui.theme.BlackN12
import com.myplanner.app.ui.theme.BoldN12
import com.myplanner.app.ui.theme.MyPlannerTheme
import com.myplanner.app.ui.theme.blue
import com.myplanner.app.ui.theme.red
import com.myplanner.app.util.NoRippleIndication
import com.myplanner.app.util.calendarDefaultBackground
import com.myplanner.app.util.calendarSelectBackground
import com.myplanner.app.util.calendarSelectTextColor
import com.myplanner.app.util.textColor
import com.myplanner.app.viewmodel.CalendarViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalCalendar(
    modifier: Modifier = Modifier.fillMaxHeight(),
    config: HorizontalCalendarConfig = HorizontalCalendarConfig(),
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val state = viewModel.date.observeAsState()
    val selectedDate = viewModel.getViewModelDate()
    //page는 0부터 시작하기 때문에 getMonthValue - 1을 해줘야 함
    val initialPage = ((state.value?.year ?: 2023) - config.yearRange.first) * 12 + (state.value?.monthValue
        ?: 13) - 1
    var currentMonth by remember { mutableStateOf(state.value) }
    var currentPage by remember { mutableStateOf(initialPage) }
    val pagerState = rememberPagerState(initialPage = initialPage)

    LaunchedEffect(pagerState.currentPage) {
        val addMonth = (pagerState.currentPage - currentPage).toLong()
        currentMonth = currentMonth?.plusMonths(addMonth)
        currentPage = pagerState.currentPage
    }

    Column(modifier = modifier) {
        val headerText = currentMonth?.dateFormat("yyyy년 M월")
        val pageCount = (config.yearRange.last - config.yearRange.first) * 12
        CalendarHeader(
            modifier = Modifier.padding(20.dp),
            text = headerText.toString()
        )
        HorizontalPager(
            pageCount = pageCount,
            state = pagerState
        ) { page ->
            val date = LocalDate.of(
                config.yearRange.first + page / 12,
                page % 12 + 1,
                1
            )
            if (page in pagerState.currentPage - 1..pagerState.currentPage + 1) {
                CalendarMonthItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    currentDate = date,
                    selectedDate = selectedDate,
                    onSelectedDate = viewModel::onChangeDate
                )
            }
        }
    }
}

@Composable
fun CalendarHeader(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(modifier = modifier) {
        Text(
            text = text,
            style = BlackN12,
            fontSize = 24.sp,
            color = textColor()
        )
    }
}

@Composable
fun CalendarMonthItem(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    selectedDate: LiveData<LocalDate>,
    onSelectedDate: (LocalDate) -> Unit
) {
    val lastDay by remember { mutableStateOf(currentDate.lengthOfMonth()) }
    val firstDayOfWeek by remember { mutableStateOf(currentDate.dayOfWeek.value) }
    val days by remember { mutableStateOf(IntRange(1, lastDay).toList()) }

    Column(modifier = modifier) {
        CalendarWeek()
        LazyVerticalGrid(
            modifier = Modifier.height(500.dp),
            columns = GridCells.Fixed(7)
        ) {
            for (i in 1..firstDayOfWeek) { // 처음 날짜가 시작하는 요일 전까지 빈 박스 생성
                item {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp)
                    )
                }
            }
            items(days) { day ->
                val date = currentDate.withDayOfMonth(day)
                val isSelected = selectedDate.value?.compareTo(date) == 0
                CalendarDay(
                    modifier = Modifier.padding(top = 10.dp),
                    date = date,
                    isToday = date == LocalDate.now(),
                    isSelected = isSelected,
                    onSelectedDate = onSelectedDate
                )
            }
        }
    }
}

@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isToday: Boolean,
    isSelected: Boolean,
    hasEvent: Boolean = false,
    onSelectedDate: (LocalDate) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val noRipple = NoRippleIndication
    Column(
        modifier = modifier
            .wrapContentSize()
            .size(70.dp)
            .padding(1.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = noRipple
            ) {

                onSelectedDate(date)
            }
            .background(if (isSelected) calendarSelectBackground() else calendarDefaultBackground())
            .border(1.dp, textColor(), RoundedCornerShape(5.dp)),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top

    ) {

        val defaultColor = textColor()
        val selectColor = calendarSelectTextColor()
        val textColor = if (isSelected) selectColor else defaultColor
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            text = date.dayOfMonth.toString(),
            style = BoldN12,
            color = if(date.dayOfWeek.value==7) red else if(date.dayOfWeek.value==6) blue else textColor
        )
        if (hasEvent) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
        }
    }
}

@Composable
fun CalendarWeek(
    modifier: Modifier = Modifier
) {
    val weekStr = listOf<String>("일","월","화","수","목","금","토")
    Row(modifier) {
        for (i in 0 until Calendar.DAY_OF_WEEK) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = weekStr[i],
                //text = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN),
                style = BlackN12,
                textAlign = TextAlign.Center,
                color = if(i==0) red else if(i==6) blue else textColor()
            )
        }
    }
}
data class HorizontalCalendarConfig(
    val yearRange: IntRange = IntRange(1970, 2100),
    val locale: Locale = Locale.KOREAN
)

fun YearMonth.dateFormat(pattern: String, locale: Locale = Locale.getDefault()): String =
    format(DateTimeFormatter.ofPattern(pattern, locale))

fun LocalDate.dateFormat(pattern: String, locale: Locale = Locale.getDefault()): String =
    format(DateTimeFormatter.ofPattern(pattern, locale))

fun LocalTime.timeFormat(pattern: String, locale: Locale = Locale.getDefault()): String =
    format(DateTimeFormatter.ofPattern(pattern, locale))

@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    MyPlannerTheme {
        HorizontalCalendar()
    }
}