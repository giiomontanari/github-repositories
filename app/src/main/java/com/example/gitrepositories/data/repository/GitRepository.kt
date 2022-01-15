package com.example.gitrepositories.data.repository

import com.example.gitrepositories.data.GitRepositoriesApi
import kotlinx.coroutines.flow.flow

class GitRepository(
    private val api: GitRepositoriesApi
) {

    suspend fun fetchGithub() = flow {
        val result = api.fetchGitHub()
        emit(result)
    }
}