package com.example.gitrepositories.data

import com.example.gitrepositories.data.dto.GitHubRepositoriesDTO
import retrofit2.http.GET

interface GitRepositoriesApi {

    @GET("search/repositories?q=language:Kotlin&sort=stars&page=")
    suspend fun fetchGitHub() : GitHubRepositoriesDTO
}