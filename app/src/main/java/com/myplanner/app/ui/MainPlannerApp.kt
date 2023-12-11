package com.myplanner.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.myplanner.app.R
import com.myplanner.app.util.CALENDAR
import com.myplanner.app.util.MEMO
import com.myplanner.app.util.NOTI
import com.myplanner.app.util.SETTING

@Composable
fun MainPlannerApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Box(Modifier.padding(it)) {
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.Calendar,
        BottomNavItem.Todo,
        BottomNavItem.Memo,
        BottomNavItem.Noti,
        BottomNavItem.Setting,
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF3F414E)
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier
                            .width(26.dp)
                            .height(26.dp)
                    )
                },
                label = { Text(stringResource(id = item.title), fontSize = 9.sp) },
                selectedContentColor = Color(0xFF26A69A),
                unselectedContentColor = Color.Gray,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


sealed class BottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String
) {
    object Calendar : BottomNavItem(R.string.nav_calendar, R.drawable.ic_launcher_foreground, CALENDAR)
    object Todo : BottomNavItem(
        R.string.nav_todo, R.drawable.ic_launcher_foreground,
        com.myplanner.app.util.TODO
    )
    object Memo : BottomNavItem(R.string.nav_memo, R.drawable.ic_launcher_foreground, MEMO)
    object Noti : BottomNavItem(R.string.nav_noti, R.drawable.ic_launcher_foreground, NOTI)
    object Setting : BottomNavItem(R.string.nav_setting, R.drawable.ic_launcher_foreground, SETTING)
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Calendar.screenRoute) {

        // 라우트 이름 = 화면의 키
        composable(BottomNavItem.Calendar.screenRoute) {
            // 화면 = 값
            CalendarScreen()
        }
        // 라우트 이름 = 화면의 키
        composable(BottomNavItem.Todo.screenRoute) {
            // 화면 = 값
            TodoScreen()
        }
        // 라우트 이름 = 화면의 키
        composable(BottomNavItem.Memo.screenRoute) {
            // 화면 = 값
            MemoScreen()
        }
        // 라우트 이름 = 화면의 키
        composable(BottomNavItem.Noti.screenRoute) {
            // 화면 = 값
            NotiScreen()
        }
        // 라우트 이름 = 화면의 키
        composable(BottomNavItem.Setting.screenRoute) {
            // 화면 = 값
            SettingScreen()
        }
    }
}
