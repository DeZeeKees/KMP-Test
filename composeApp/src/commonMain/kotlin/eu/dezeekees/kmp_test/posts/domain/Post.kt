package eu.dezeekees.kmp_test.posts.domain

import eu.dezeekees.kmp_test.posts.data.remote.dto.PostCommentResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Post {

    var userId: Int
        private set

    var id: Int
        private set

    var title: String
        private set

    var body: String
        private set

    private val _postComments = MutableStateFlow<List<PostCommentResponse>>(emptyList())
    val postComments: StateFlow<List<PostCommentResponse>> = _postComments

    constructor(
        userId: Int = 0,
        id: Int = 0,
        title: String = "",
        body: String = "",
    ) {
        this.userId = userId
        this.id = id
        this.title = title
        this.body = body
    }

    suspend fun getComments(
        postId: Int,
        service: PostsService
    ) {
        _postComments.value = service.getComments(postId)
    }

    fun collect(any: Any) {

    }
}