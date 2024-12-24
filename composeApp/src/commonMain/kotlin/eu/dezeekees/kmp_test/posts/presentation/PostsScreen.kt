package eu.dezeekees.kmp_test.posts.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import eu.dezeekees.kmp_test.posts.presentation.components.PostsComponent
import eu.dezeekees.kmp_test.posts.presentation.events.PostsEvent
import eu.dezeekees.kmp_test.posts.presentation.ui.PostCard

@Composable
fun PostsScreen(
    component: PostsComponent
) {
    val posts by component.posts.subscribeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text("Posts")

        LazyVerticalGrid(
            columns = GridCells.Adaptive(
                minSize = 400.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(posts) { post ->
                PostCard(
                    post,
                    onClick = { component.onEvent(PostsEvent.ClickPost(postId = post.id)) }
                )
            }
        }
    }
}