package eu.dezeekees.kmp_test.posts.presentation.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import eu.dezeekees.kmp_test.posts.data.remote.dto.PostCommentResponse
import eu.dezeekees.kmp_test.posts.domain.Post
import eu.dezeekees.kmp_test.posts.domain.PostsService
import eu.dezeekees.kmp_test.posts.presentation.events.PostDetailsEvent
import eu.dezeekees.kmp_test.posts.presentation.events.PostsEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PostDetailsComponent(
    componentContext: ComponentContext,
    val postsService: PostsService,
    val postId: Int,
    val popBackStack: () -> Unit
): ComponentContext by componentContext {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _post = MutableStateFlow(Post())
    val post: StateFlow<Post> = _post

    @OptIn(ExperimentalCoroutinesApi::class)
    val postComments: StateFlow<List<PostCommentResponse>>
        get() = _post.flatMapLatest { it.postComments }.stateIn(
            scope,
            SharingStarted.Eagerly,
            emptyList()
        )

    private var isLoading = false

    init {
        loadPost()
    }

    private fun loadPost() {
        if(isLoading) return
        isLoading = true

        scope.launch {
            val post = postsService.getPost(postId = postId)
            post.getComments(postId, postsService)
            _post.value = post
        }
    }

    fun onEvent(event: PostDetailsEvent) {
        when(event){
            is PostDetailsEvent.GoBack -> popBackStack()
        }
    }
}