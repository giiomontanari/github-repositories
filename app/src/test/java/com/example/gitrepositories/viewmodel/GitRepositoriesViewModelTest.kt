package com.example.gitrepositories.viewmodel

import androidx.lifecycle.Observer
import com.example.gitrepositories.data.dto.GitHubRepositoriesDTO
import com.example.gitrepositories.data.repository.GitRepository
import com.example.gitrepositories.ui.viewmodel.GitRepositoriesViewModel
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest

class GitRepositoriesViewModelTest : KoinTest {

    private lateinit var viewModel: GitRepositoriesViewModel

    private val repository = mockk<GitRepository>()

    @Before
    fun setUp() {
        viewModel = GitRepositoriesViewModel(
            repository
        )
    }

    @Test
    fun whenFetchGithub_ShouldCallMethodFetchGithubOnObserver() {
        val observer = mockk<Observer<GitHubRepositoriesDTO>>()

        coEvery { repository.fetchGithub() } returns mockk(relaxUnitFun = true)

        viewModel.githubReceived.observeForever(observer)

        viewModel.getGithub()

        coVerify { repository.fetchGithub() }
    }
}