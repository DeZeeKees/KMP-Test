package eu.dezeekees.kmp_test.posts.domain

import eu.dezeekees.kmp_test.posts.data.remote.dto.PostCommentResponse

interface PostsService {
    suspend fun getPosts(): List<Post>
    suspend fun getPost(postId: Int): Post
    suspend fun getComments(postId: Int): List<PostCommentResponse>

    companion object {
        fun ktorCreate(
            postsRepository: PostsRepository
        ): PostsServiceImpl {
            return PostsServiceImpl(
                postsRepository = postsRepository
            )
        }
    }
}