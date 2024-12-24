package eu.dezeekees.kmp_test.posts.data.remote

import eu.dezeekees.kmp_test.posts.data.remote.dto.PostCommentResponse
import eu.dezeekees.kmp_test.posts.data.remote.dto.PostResponse
import eu.dezeekees.kmp_test.posts.domain.Post
import eu.dezeekees.kmp_test.posts.domain.PostsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorPostsRepository(
    private val client: HttpClient,
    private val baseUrl: String
): PostsRepository {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get("$baseUrl/posts").body()
        } catch (e: Exception) {
            return emptyList()
        }
    }

    override suspend fun getPost(postId: Int): PostResponse {
        return try {
            client.get("$baseUrl/posts/$postId").body()
        } catch (e: Exception) {
            return PostResponse()
        }
    }

    override suspend fun getComments(postId: Int): List<PostCommentResponse> {
        return try {
            client.get("$baseUrl/posts/$postId/comments").body()
        } catch (e: Exception) {
            return emptyList()
        }
    }
}