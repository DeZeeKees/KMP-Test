package eu.dezeekees.kmp_test

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import eu.dezeekees.kmp_test.navigation.RootComponent
import eu.dezeekees.kmp_test.utils.runOnUiThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()

    val root =
        runOnUiThread {
            RootComponent(
                DefaultComponentContext(
                    lifecycle = lifecycle
                )
            )
        }

    application {

        var useCustomTitleBar by remember { mutableStateOf(false) }

        Window(
            onCloseRequest = ::exitApplication,
            title = "KMP Test",
            undecorated = useCustomTitleBar
        ) {
            val windowState = rememberWindowState()

            LifecycleController(lifecycle, windowState)

            Column(
                modifier = Modifier.onPointerEvent(PointerEventType.Release) { event ->
                    if (event.button == PointerButton(3)) {
                        root.popBackStack()
                    }
                }
            ) {
                if(useCustomTitleBar) TitleBar()

                App(root)
            }
        }
    }
}


