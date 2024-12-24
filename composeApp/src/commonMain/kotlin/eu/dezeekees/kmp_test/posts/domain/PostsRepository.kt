package eu.dezeekees.kmp_test.posts.domain

import eu.dezeekees.kmp_test.posts.data.remote.KtorPostsRepository
import eu.dezeekees.kmp_test.posts.data.remote.dto.PostCommentResponse
import eu.dezeekees.kmp_test.posts.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import co.touchlab.kermit.Logger as Kermit

interface PostsRepository {
    suspend fun getPosts(): List<PostResponse>
    suspend fun getPost(postId: Int): PostResponse
    suspend fun getComments(postId: Int): List<PostCommentResponse>

    companion object {
        const val baseUrl = "https://jsonplaceholder.typicode.com"
        fun ktorCreate(): KtorPostsRepository {
            return KtorPostsRepository(
                client = HttpClient(CIO) {
                    install(Logging) {
                        logger = object: Logger {
                            override fun log(message: String) {
                                Kermit.v("HTTP Client:", null, message)
                            }
                        }
                        level = LogLevel.HEADERS
                    }
                    install(ContentNegotiation) {
                        json()
                    }
                },
                baseUrl = baseUrl
            )
        }
    }
}