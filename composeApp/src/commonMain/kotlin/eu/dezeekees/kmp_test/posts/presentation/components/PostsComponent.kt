package eu.dezeekees.kmp_test.posts.presentation.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import eu.dezeekees.kmp_test.posts.domain.Post
import eu.dezeekees.kmp_test.posts.domain.PostsService
import eu.dezeekees.kmp_test.posts.presentation.events.PostsEvent
import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostsComponent(
    componentContext: ComponentContext,
    private val postsService: PostsService,
    private val onNavigateToPostDetailsScreen: (Int) -> Unit
): ComponentContext by componentContext {

    private val _posts = MutableValue<List<Post>>(emptyList())
    val posts: Value<List<Post>> = _posts

    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        loadPosts()
    }

    private fun loadPosts() {
        scope.launch {
            _posts.value = postsService.getPosts()
        }
    }

    fun onEvent(event: PostsEvent) {
        when(event){
            is PostsEvent.ClickPost -> onNavigateToPostDetailsScreen(event.postId)
        }
    }
}