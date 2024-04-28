package com.haroldcalayan.tamingtemper.presenter.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse

@Composable
fun WeekTabLayout(state: TamingActivityResponse?) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.Black
        ) {
            daysOfWeek.forEachIndexed { index, day ->
                Tab(
                    text = {
                        Text(text = day, fontSize = 11.sp, color = Color.Black)
                    },
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                )
            }
        }
        Chapter(state)
    }
}
