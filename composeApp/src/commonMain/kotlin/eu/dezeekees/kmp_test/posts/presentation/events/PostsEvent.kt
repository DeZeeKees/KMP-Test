package eu.dezeekees.kmp_test.posts.presentation.events

sealed interface PostsEvent {
    data class ClickPost(val postId: Int): PostsEvent
}