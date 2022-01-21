package com.example.gitrepositories.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.gitrepositories.data.dto.GitHubRepositoriesDTO
import com.example.gitrepositories.data.repository.GitRepository
import io.mockk.*
import com.example.gitrepositories.data.dto.GitHubItemsDTO
import com.example.gitrepositories.data.dto.OwnerDTO
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
class GitRepositoriesViewModelTest : KoinTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var viewModel: GitRepositoriesViewModel

    private val repository = mockk<GitRepository>()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = GitRepositoriesViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun whenFetchGithub_ShouldCallMethodFetchGithubOnObserverIsChanged() = runBlocking {
        val observer = mockk<Observer<GitViewStates<GitHubRepositoriesDTO>>>()

        coEvery { repository.fetchGithub() }  returns flow {
            emit(GitHubRepositoriesDTO(
                items = listOf(
                    GitHubItemsDTO(
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
                )
            ))
        }

        viewModel.githubReceived.observeForever(observer)

        viewModel.getGithub()

        coVerify { observer.onChanged(any()) }
    }
}