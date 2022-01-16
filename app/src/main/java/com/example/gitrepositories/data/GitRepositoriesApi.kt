package com.example.gitrepositories.data

import com.example.gitrepositories.data.dto.GitHubRepositoriesDTO
import com.example.gitrepositories.data.dto.GitItemPullRequestDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GitRepositoriesApi {

    @GET("search/repositories?q=language:Kotlin&sort=stars&page=")
    suspend fun fetchGitHub() : GitHubRepositoriesDTO

    @GET("repos/{nameAuthor}/{nameRepo}/pulls")
    suspend fun fetchPullRequests(
        @Path("nameRepo") nameRepo: String?,
        @Path("nameAuthor") nameAuthor: String?
    ) : List<GitItemPullRequestDTO>
}