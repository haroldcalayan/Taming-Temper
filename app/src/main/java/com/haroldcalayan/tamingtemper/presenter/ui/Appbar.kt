package com.haroldcalayan.tamingtemper.presenter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haroldcalayan.tamingtemper.R

@Preview
@Composable
fun Appbar() {
    val color = MaterialTheme.colorScheme.primary
    val progressPercentage = 3f
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.journey_con),
                contentDescription = "App Logo",
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = "Taming temper", fontSize = 18.sp, color = color)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .height(4.dp)
                            .width(100.dp),
                        color = color,
                        trackColor = Color.Gray,
                        progress = progressPercentage / 100f
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "$progressPercentage%",
                        fontSize = 12.sp,
                        color = color
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.fire),
                contentDescription = "fire",
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "0",
                fontSize = 16.sp,
                color = color
            )

            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.trailing),
                contentDescription = "profile",
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Fit
            )

        }
    }
}