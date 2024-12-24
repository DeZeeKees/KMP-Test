package eu.dezeekees.kmp_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.retainedComponent
import eu.dezeekees.kmp_test.navigation.RootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val root = retainedComponent {
            RootComponent(it)
        }

        setContent {
            Box(modifier = Modifier.safeDrawingPadding()) {
                App(root)
            }
        }
    }
}