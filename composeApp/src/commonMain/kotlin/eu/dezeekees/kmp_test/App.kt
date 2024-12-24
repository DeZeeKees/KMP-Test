package eu.dezeekees.kmp_test

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import eu.dezeekees.kmp_test.navigation.RootComponent
import eu.dezeekees.kmp_test.posts.presentation.PostDetailsScreen
import eu.dezeekees.kmp_test.posts.presentation.PostsScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(root: RootComponent) {
    MaterialTheme {
        val childStack by root.childStack.subscribeAsState()

        Scaffold {
            Children(
                stack = childStack,
                animation = stackAnimation(fade())
            ) { child ->
                when(val instance = child.instance) {
                    is RootComponent.Child.PostsScreen -> PostsScreen(instance.component)
                    is RootComponent.Child.PostDetailsScreen -> PostDetailsScreen(instance.component)
                }
            }
        }
    }
}