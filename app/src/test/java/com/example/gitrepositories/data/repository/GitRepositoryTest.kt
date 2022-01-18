package com.example.gitrepositories.data.repository

import com.example.gitrepositories.data.GitRepositoriesApi
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GitRepositoryTest {

    private val mockApi = mockk<GitRepositoriesApi>()

    private val repository = GitRepository(mockApi)

    @Test
    fun whenGetRepositories_callApiWithCorrect() = runBlocking {
        launch(Dispatchers.Main) {
            repository.fetchGithub()
            coVerify { mockApi.fetchGitHub() }
        }
    }
}