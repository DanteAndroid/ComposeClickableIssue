package com.example.composeclickable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DialogBlink() {
    var showFirst by remember {
        mutableStateOf(false)
    }
    var showSecond by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(
            onClick = {
                showFirst = true
            }
        ) {
            Text(text = "Show Dialog")
        }
        if (showFirst) {
            Dialog(onDismissRequest = { showFirst = false }) {
                Box(
                    Modifier
                        .size(400.dp, 150.dp)
                        .background(Color.White)
                ) {
                    Button(onClick = {
                        showSecond = true
                    }, modifier = Modifier.align(Alignment.Center)) {
                        Text(text = "show second")
                    }
                }
            }
        }
        if (showSecond) {
            Dialog(onDismissRequest = { showSecond = false }) {
                Box(
                    Modifier
                        .size(400.dp, 200.dp)
                        .background(Color.White)
                ) {
                    Text(text = "This is \n second dialog")
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogBlinkPreview() {
    DialogBlink()
}
