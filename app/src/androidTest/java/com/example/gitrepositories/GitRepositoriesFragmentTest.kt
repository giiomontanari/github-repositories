package com.example.gitrepositories

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitrepositories.data.dto.GitHubItemsDTO
import com.example.gitrepositories.data.dto.GitHubRepositoriesDTO
import com.example.gitrepositories.data.dto.OwnerDTO
import com.example.gitrepositories.data.repository.GitRepository
import com.example.gitrepositories.platform.KoinTestApp
import com.example.gitrepositories.ui.view.GitRepositoriesFragment
import com.example.gitrepositories.ui.viewmodel.GitRepositoriesViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class GitRepositoriesFragmentTest {

    private lateinit var viewModel: GitRepositoriesViewModel

    private val repository = mockk<GitRepository>()

    @After
    fun tearDown() {
        stopKoin()
    }

    @Before
    fun setUp() {
        mockKoin()
    }

    @Test
    fun givenSuccessResponse_ShouldDisplayRecyclerView() {

        coEvery { repository.fetchGithub() } returns flow {
            emit(GitHubRepositoriesDTO(
                items = createList()
            ))
        }

        prepare()

        launch()

        onView(withId(R.id.recycler_github))
            .check(matches(isDisplayed()))
    }

    private fun prepare() {
        viewModel = GitRepositoriesViewModel(repository)
    }

    private fun createList(): List<GitHubItemsDTO> {
        val list = mutableListOf<GitHubItemsDTO>()

        repeat(10) {
            val item = GitHubItemsDTO(
                name = "okhttp",
                description = "Square’s meticulous HTTP client for the JVM, Android, and GraalVM.",
                language = "Kotlin",
                forksCount = 8732,
                owner = OwnerDTO(
                    avatarUrl = "https://avatars.githubusercontent.com/u/82592?v=4",
                    eventsUrl = "https://api.github.com/users/square/events{/privacy}",
                    followersUrl = "https://api.github.com/users/square/followers",
                    followingUrl = "https://api.github.com/users/square/following{/other_user}",
                    gistsUrl = "https://api.github.com/users/square/gists{/gist_id}",
                    gravatarId = "",
                    htmlUrl = "https://github.com/square",
                    id = 82592,
                    login = "square",
                    nodeId = "MDEyOk9yZ2FuaXphdGlvbjgyNTky",
                    organizationsUrl = "https://api.github.com/users/square/orgs",
                    receivedEventsUrl = "https://api.github.com/users/square/received_events",
                    reposUrl = "https://api.github.com/users/square/repos",
                    siteAdmin = false,
                    starredUrl = "https://api.github.com/users/square/starred{/owner}{/repo}",
                    subscriptionsUrl = "https://api.github.com/users/square/subscriptions",
                    type = "Organization",
                    url = "https://api.github.com/users/square",
                ),
                stargazersCount = 41455,
                watchersCount = 41455
            )
        }

        return list
    }

    private fun launch() {
        launchFragmentInContainer<GitRepositoriesFragment>(
            themeResId = R.style.Theme_GitRepositories
        )
    }

    private fun mockKoin() {
        ApplicationProvider.getApplicationContext<KoinTestApp>()
            .injectModule(module {
                single {
                    viewModel
                }
            })
    }
}