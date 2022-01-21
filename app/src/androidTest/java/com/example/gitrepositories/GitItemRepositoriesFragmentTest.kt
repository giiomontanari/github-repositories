package com.example.gitrepositories

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gitrepositories.data.dto.*
import com.example.gitrepositories.data.repository.GitRepository
import com.example.gitrepositories.platform.KoinTestApp
import com.example.gitrepositories.ui.view.GitItemRepositoriesFragment
import com.example.gitrepositories.ui.viewmodel.GitItemRepositoriesViewModel
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
class GitItemRepositoriesFragmentTest {

    private lateinit var viewModel: GitItemRepositoriesViewModel

    private val repository = mockk<GitRepository>()

    private val NAME = ""

    private val NAME_AUTHOR = ""

    val args = Bundle().apply {
        putString(NAME, "okhttp")
        putString(NAME_AUTHOR, "square")
    }

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

        coEvery { repository.fetchPullRequests(any(), any()) } returns flow {
            emit(createList())
        }

        prepare()

        launch()

        Espresso.onView(ViewMatchers.withId(R.id.recycler_pull))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun prepare() {
        viewModel = GitItemRepositoriesViewModel(repository)
    }

    private fun createList(): List<GitItemPullRequestDTO> {
        val list = mutableListOf<GitItemPullRequestDTO>()

        repeat(10) {
            val item = GitItemPullRequestDTO(
                body = "Body Test",
                dateCreated = "2021-10-20T09:14:53Z",
                head = BaseDTO(
                    repo = RepoDTO(
                        name = "architecture-samples",
                        owner = OwnerDTO(
                            avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4",
                            eventsUrl = "https://api.github.com/users/android/events{/privacy}",
                            followersUrl = "https://api.github.com/users/android/followers",
                            followingUrl = "https://api.github.com/users/android/following{/other_user}",
                            gistsUrl = "https://api.github.com/users/android/gists{/gist_id}",
                            gravatarId = "",
                            htmlUrl = "https://github.com/android",
                            id = 32689599,
                            login = "android",
                            nodeId = "MDEyOk9yZ2FuaXphdGlvbjMyNjg5NTk5",
                            organizationsUrl = "https://api.github.com/users/android/orgs",
                            receivedEventsUrl = "https://api.github.com/users/android/received_events",
                            reposUrl = "https://api.github.com/users/android/repos",
                            siteAdmin = false,
                            starredUrl = "https://api.github.com/users/android/starred{/owner}{/repo}",
                            subscriptionsUrl = "https://api.github.com/users/android/subscriptions",
                            type = "Organization",
                            url = "https://api.github.com/users/android",
                        )
                    ),
                    user = OwnerDTO(
                        avatarUrl = "https://avatars.githubusercontent.com/u/32689599?v=4",
                        eventsUrl = "https://api.github.com/users/android/events{/privacy}",
                        followersUrl = "https://api.github.com/users/android/followers",
                        followingUrl = "https://api.github.com/users/android/following{/other_user}",
                        gistsUrl = "https://api.github.com/users/android/gists{/gist_id}",
                        gravatarId = "",
                        htmlUrl = "https://github.com/android",
                        id = 32689599,
                        login = "android",
                        nodeId = "MDEyOk9yZ2FuaXphdGlvbjMyNjg5NTk5",
                        organizationsUrl = "https://api.github.com/users/android/orgs",
                        receivedEventsUrl = "https://api.github.com/users/android/received_events",
                        reposUrl = "https://api.github.com/users/android/repos",
                        siteAdmin = false,
                        starredUrl = "https://api.github.com/users/android/starred{/owner}{/repo}",
                        subscriptionsUrl = "https://api.github.com/users/android/subscriptions",
                        type = "Organization",
                        url = "https://api.github.com/users/android"
                    )
                ),
                htmlUrl = "https://github.com/android/architecture-samples/pull/808",
                titlePullRequest = "Updated Readme Links & Commands",
                urlPullRequest = "https://api.github.com/repos/android/architecture-samples/pulls/808",
                user = OwnerDTO(
                    avatarUrl = "https://avatars.githubusercontent.com/u/67560900?v=4",
                    eventsUrl = "https://api.github.com/users/SunitRoy2703/events{/privacy}",
                    followersUrl = "https://api.github.com/users/SunitRoy2703/followers",
                    followingUrl = "https://api.github.com/users/SunitRoy2703/following{/other_user}",
                    gistsUrl = "https://api.github.com/users/SunitRoy2703/gists{/gist_id}",
                    gravatarId = "",
                    htmlUrl = "https://github.com/SunitRoy2703",
                    id = 67560900,
                    login = "SunitRoy2703",
                    nodeId = "MDQ6VXNlcjY3NTYwOTAw",
                    organizationsUrl = "https://api.github.com/users/SunitRoy2703/orgs",
                    receivedEventsUrl = "https://api.github.com/users/SunitRoy2703/received_events",
                    reposUrl = "https://api.github.com/users/SunitRoy2703/repos",
                    siteAdmin = false,
                    starredUrl = "https://api.github.com/users/SunitRoy2703/starred{/owner}{/repo}",
                    subscriptionsUrl = "https://api.github.com/users/SunitRoy2703/subscriptions",
                    type = "User",
                    url = "https://api.github.com/users/SunitRoy2703"
                )
            )
        }

        return list
    }

    private fun launch() {
        launchFragmentInContainer<GitItemRepositoriesFragment>(
            args,
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