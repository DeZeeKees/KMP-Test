package eu.dezeekees.kmp_test.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import eu.dezeekees.kmp_test.posts.domain.PostsRepository
import eu.dezeekees.kmp_test.posts.domain.PostsService
import eu.dezeekees.kmp_test.posts.presentation.components.PostDetailsComponent
import eu.dezeekees.kmp_test.posts.presentation.components.PostsComponent
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext
): ComponentContext by componentContext {

    private val postsRepository = PostsRepository.ktorCreate()
    private val postsService = PostsService.ktorCreate(postsRepository)

    private val navigation = StackNavigation<Config>()
    val childStack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.PostsScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    fun popBackStack() {
        navigation.pop()
    }

    private val postDetailsComponents = mutableMapOf<Int, PostDetailsComponent>()

    private fun getPostDetailsComponent(postId: Int): PostDetailsComponent {
        return postDetailsComponents.getOrPut(postId) {
            PostDetailsComponent(
                componentContext = this,
                postsService = postsService,
                postId = postId,
                popBackStack = {
                    navigation.pop()
                }
            )
        }
    }

    private fun createChild(
        config: Config,
        context: ComponentContext
    ): Child {
        return when(config) {
            Config.PostsScreen -> Child.PostsScreen(
                PostsComponent(
                    componentContext = context,
                    postsService = postsService,
                    onNavigateToPostDetailsScreen = { postId -> navigation.pushNew(Config.PostDetailsScreen(postId)) }
                )
            )

            is Config.PostDetailsScreen -> Child.PostDetailsScreen(
                getPostDetailsComponent(config.postId)
            )
        }
    }

    sealed class Child {
        data class PostsScreen(val component: PostsComponent): Child()
        data class PostDetailsScreen(val component: PostDetailsComponent): Child()
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object PostsScreen: Config()

        @Serializable
        data class PostDetailsScreen(val postId: Int): Config()
    }
}