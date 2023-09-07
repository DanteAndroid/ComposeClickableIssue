package com.example.composeclickable.ui.data

import androidx.compose.runtime.Composable
import com.example.composeclickable.ClickablePerformance
import com.example.composeclickable.DialogBlink

/**
 * @author Du Wenyu
 * 2023/9/7
 */
data class Model(
    val title: String,
    val desc: String,
    val action: @Composable (() -> Unit)? = null
) {
    companion object {
        fun data() = listOf(
            Model(
                "ClickablePerformance",
                "When adding clickable to a list which has large data, and combine with HorizontalPager, the performance is really bad."
            ) {
                ClickablePerformance()
            }, Model(
                "DialogBlink",
                "Show first Dialog, and second, the screen blink just before second Dialog shows"
            ) {
                DialogBlink()
            })
    }
}
