package eu.dezeekees.kmp_test.posts.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostCommentResponse(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)