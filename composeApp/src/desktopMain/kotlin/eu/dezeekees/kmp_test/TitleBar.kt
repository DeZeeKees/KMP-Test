package eu.dezeekees.kmp_test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope

@Composable
fun WindowScope.TitleBar() = WindowDraggableArea {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(Color.DarkGray)
    )
}
