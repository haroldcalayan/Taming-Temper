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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.AsyncImage
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
                    title = it.title.orEmpty(),
                    description = it.description.orEmpty(),
                    level = it.level.orEmpty()
                )
                ActivityItems(activities = it.activities, it.state.orEmpty() == "LOCKED")
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
        Text(
            text = description,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun ActivityItems(activities: List<Activity>?, isLocked: Boolean) {
    Column {
        activities.orEmpty().groupIntoPairs().map {
            val equalModifier = Modifier.weight(1f)
            if (it.second != null) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ActivityItemContent(it.first, equalModifier, isLocked)
                    ActivityItemContent(it.second!!, equalModifier, isLocked)
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ActivityItemContent(it.first, Modifier.fillMaxWidth(.5f), isLocked)
                }
            }
        }
    }
}

@Composable
fun ActivityItemContent(activity: Activity, equalModifier: Modifier, isLocked: Boolean) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = equalModifier.padding(16.dp)
    ) {
        val textModifier = Modifier.fillMaxWidth()

        val imageUrl = if (isLocked) {
            activity.lockedIcon?.file?.url
        } else activity.icon?.file?.url
        AsyncImage(
            model = imageUrl,
            placeholder = painterResource(id = R.drawable.baseline_photo),
            error = painterResource(id = R.drawable.baseline_photo),
            contentDescription = "Activity Icon",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = activity.title ?: "",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = textModifier
        )
    }
}

fun <T> List<T>.groupIntoPairs(): List<Pair<T, T?>> {
    return this.chunked(2) { chunk ->
        when (chunk.size) {
            2 -> chunk[0] to chunk[1]
            1 -> chunk[0] to null
            else -> throw IllegalStateException("Unexpected chunk size: ${chunk.size}")
        }
    }
}

