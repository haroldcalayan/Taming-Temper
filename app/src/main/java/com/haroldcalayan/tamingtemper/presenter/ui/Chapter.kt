package com.haroldcalayan.tamingtemper.presenter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haroldcalayan.tamingtemper.R
import com.haroldcalayan.tamingtemper.data.source.remote.model.Activity
import com.haroldcalayan.tamingtemper.data.source.remote.model.TamingActivityResponse

@Composable
fun Chapter(state: TamingActivityResponse?) {
    LazyColumn(
        modifier = Modifier
            .background(Color.White),
        verticalArrangement = Arrangement.Top
    ) {
            items(state?.levels.orEmpty()) {
                Column {
                    Level(
                        title = it.title ?: "",
                        description = it.description ?: "",
                        level = it.level ?: ""
                    )
                    ActivityItem(activities = it.activities)
                }
            }

    }
}

@Composable
fun Level(
    title: String,
    description: String,
    level: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.chapter),
                contentDescription = "level_icon",
                modifier = Modifier
                    .size(width = 102.dp, height = 86.dp)
                    .offset(y = (-10).dp),
                contentScale = ContentScale.Fit
            )
            Box(
                modifier = Modifier
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            ) {
                Text(
                    text = "LEVEL $level",
                    color = Color.White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = description, textAlign = TextAlign.Center, fontSize = 14.sp, color = Color.LightGray)
    }
}

@Composable
fun ActivityItem(activities: List<Activity>?) {
    Column {
        Spacer(modifier = Modifier.height(15.dp))
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(250.dp)
        ) {

            items(activities ?: emptyList()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lesson_not_selected),
                        contentDescription = "activity_icon",
                        modifier = Modifier.size(width = 102.dp, height = 86.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = it.title ?: "", fontSize = 14.sp, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

