package com.example.gitrepositories.data.dto

import com.google.gson.annotations.SerializedName

data class GitHubItemsDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: OwnerDTO,
    @SerializedName("description")
    val description: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("forks_count")
    val forksCount: Int,
)
