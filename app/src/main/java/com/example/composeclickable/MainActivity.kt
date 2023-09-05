package com.example.composeclickable

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeclickable.ui.theme.ComposeClickableTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeClickableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier) {
                        HorizontalPager(pageCount = 20) {
                            Table(it)
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    applicationContext,
                                    MainActivity2::class.java
                                )
                            )
                        }) {
                            Text(text = "Go")
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Table(i: Int, clickable: Boolean = true) {
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
                            }  else it
                        }){
                        Text(text = "$i")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeClickableTheme {
        Table(0)
    }
}