package com.example.composeclickable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeclickable.ui.data.Model
import com.example.composeclickable.ui.theme.ComposeClickableTheme


class MainActivity : ComponentActivity() {

    @OptIn(
        ExperimentalAnimationApi::class,
        ExperimentalComposeUiApi::class,
        ExperimentalFoundationApi::class,
        ExperimentalMaterial3Api::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeClickableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold { paddingValues: PaddingValues ->
                        TutorialNavGraph(modifier = Modifier.padding(paddingValues))
                    }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun TutorialNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.BASICS_START,
    data: List<Model> = Model.data()
) {
    NavHost(
        modifier = modifier.statusBarsPadding(),
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Destinations.BASICS_START) { _ ->
            MainList(data) { title ->
                navController.navigate(title)
            }
        }
        data.forEach { model ->
            composable(route = model.title) { _ ->
                // This column is used for setting navigation padding since
                // NavHost only has statusBarsPadding to let main screen list have
                // inset at the bottom with WindowInsetsSides.Bottom
                Column(Modifier.navigationBarsPadding()) {
                    model.action?.invoke()
                }
            }
        }
    }
}

@Composable
fun MainList(data: List<Model>, navigate: (String) -> Unit = {}) {
    LazyColumn {
        items(data.size) {
            Box(modifier = Modifier.size(200.dp, 45.dp)) {
                TextButton(onClick = {
                    navigate.invoke(data[it].title)
                }) {
                    Text(text = data[it].title)
                }
            }
        }
    }
}

object Destinations {
    const val BASICS_START = "start_destinations"
}

@Preview(showBackground = true)
@Composable
fun MainListPreview() {
    ComposeClickableTheme {
        MainList(data = Model.data())
    }
}