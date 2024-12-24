package eu.dezeekees.kmp_test.posts.data.remote.dto

import eu.dezeekees.kmp_test.posts.domain.Post
import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val body: String = ""
)

fun PostResponse.toModel(): Post {
    return Post(
        userId = this.userId,
        id = this.id,
        title = this.title,
        body = this.body
    )
}

fun List<PostResponse>.toModel(): List<Post> {
    return this.map { it.toModel() }
}
