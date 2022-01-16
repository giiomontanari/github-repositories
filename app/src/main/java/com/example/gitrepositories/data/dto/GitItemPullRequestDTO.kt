package com.example.gitrepositories.data.dto

import com.google.gson.annotations.SerializedName

data class GitItemPullRequestDTO(
    @SerializedName("url")
    val urlPullRequest: String,
    @SerializedName("title")
    val titlePullRequest: String,
    @SerializedName("user")
    val user: OwnerDTO,
    @SerializedName("body")
    val body: String,
    @SerializedName("created_at")
    val dateCreated: String
)
