package eu.dezeekees.kmp_test.posts.domain

import eu.dezeekees.kmp_test.posts.data.remote.dto.PostCommentResponse
import eu.dezeekees.kmp_test.posts.data.remote.dto.toModel

class PostsServiceImpl(
    private val postsRepository: PostsRepository
): PostsService {
    override suspend fun getPosts(): List<Post> {
        val getPostsResponse = postsRepository.getPosts()
        return getPostsResponse.toModel()
    }

    override suspend fun getPost(postId: Int): Post {
        return postsRepository.getPost(postId).toModel()
    }

    override suspend fun getComments(postId: Int): List<PostCommentResponse> {
        return postsRepository.getComments(postId)
    }
}