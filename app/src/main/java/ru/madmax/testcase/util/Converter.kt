package ru.madmax.testcase.util

import android.content.res.Resources.getSystem
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Int.toDp(): Dp {
    return (this / getSystem().displayMetrics.density).toInt().dp
}