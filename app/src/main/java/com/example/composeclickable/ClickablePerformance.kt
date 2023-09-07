package com.example.composeclickable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClickablePerformance() {
    Box(modifier = Modifier.padding(16.dp)) {
        var clickable by remember {
            mutableStateOf(true)
        }
        HorizontalPager(
            rememberPagerState { 20 },
            pageSpacing = 16.dp
        ) {
            Table(it, clickable)
        }
        Button(onClick = {
            clickable = clickable.not()
        }, modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(text = "Clickable: $clickable")
        }
    }
}

@Composable
fun Table(i: Int, clickable: Boolean) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        repeat(24) { row ->
            Row {
                repeat(7) { column ->
                    Box(modifier = Modifier
                        .background(Color.White)
                        .weight(1f)
                        .height(30.dp)
                        .border(0.5.dp, Color.LightGray)
                        .let {
                            if (clickable) it.clickable {
                                println("OnClick $row $column")
                            } else it
                        }) {
                        Text(text = "$i")
                    }
                }
            }
        }
    }
}