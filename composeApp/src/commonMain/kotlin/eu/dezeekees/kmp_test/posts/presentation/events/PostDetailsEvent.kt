package eu.dezeekees.kmp_test.posts.presentation.events

sealed interface PostDetailsEvent {
    data object GoBack: PostDetailsEvent
}