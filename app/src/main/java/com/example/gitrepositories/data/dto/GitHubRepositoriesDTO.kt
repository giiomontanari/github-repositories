package com.example.gitrepositories.data.dto

import com.google.gson.annotations.SerializedName

data class GitHubRepositoriesDTO(
    @SerializedName("items")
    val items: List<GitHubItemsDTO>
)
