package com.example.gitrepositories.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.gitrepositories.data.dto.*
import com.example.gitrepositories.data.repository.GitRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class GitItemRepositoriesViewModelTest : KoinTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var viewModel: GitItemRepositoriesViewModel

    private val repository = mockk<GitRepository>()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = GitItemRepositoriesViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun whenFetchGithub_ShouldCallMethodFetchGithubOnObserverIsChanged() = runBlocking {
        val observer = mockk<Observer<GitViewStates<List<GitItemPullRequestDTO>>>>()

        coEvery { repository.fetchPullRequests(any(), any()) }  returns flow {
            emit(listOf(
                GitItemPullRequestDTO(
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
            ))
        }

        viewModel.pullRequestsReceived.observeForever(observer)

        viewModel.getPullRequests("okhttp", "square")

        coVerify { observer.onChanged(any()) }
    }
}