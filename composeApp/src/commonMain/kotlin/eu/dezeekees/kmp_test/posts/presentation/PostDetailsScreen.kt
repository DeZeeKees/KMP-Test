package eu.dezeekees.kmp_test.posts.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import eu.dezeekees.kmp_test.posts.data.remote.dto.PostCommentResponse
import eu.dezeekees.kmp_test.posts.presentation.components.PostDetailsComponent

@Composable
fun PostDetailsScreen(
    component: PostDetailsComponent
) {
    val post by component.post.collectAsState()
    val comments by component.postComments.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Button(
            onClick = {
                component.popBackStack()
            },
            content = { Text(
                text = "Back"
            ) }
        )

        Text(
            post.title,
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            post.body,
            style = MaterialTheme.typography.bodyLarge
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(comments) {
                Card(modifier = Modifier.padding(4.dp)) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            it.name,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            it.email,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(it.body)
                    }
                }
            }
        }
    }
}