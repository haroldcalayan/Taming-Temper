package com.haroldcalayan.tamingtemper.presenter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse

@Composable
fun WeekTabLayout(state: TamingActivityResponse?) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val selectedDay = remember { mutableStateOf(daysOfWeek[selectedTabIndex]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            daysOfWeek.forEachIndexed { index, day ->
                RadioButton(
                    selected = selectedDay.value == day,
                    onClick = {
                        selectedDay.value = day
                        selectedTabIndex = index
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = Color.Gray
                    )
                )
            }
        }
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.Black
        ) {
            daysOfWeek.forEachIndexed { index, day ->
                Tab(
                    text = {
                        Text(
                            text = day,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (selectedTabIndex == index) {
                                MaterialTheme.colorScheme.primary
                            } else Color.Gray
                        )
                    },
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        selectedDay.value = day
                    }
                )
            }
        }
        Chapter(state)
    }
}
