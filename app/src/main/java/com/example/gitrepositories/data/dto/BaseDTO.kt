package com.example.gitrepositories.data.dto

import com.google.gson.annotations.SerializedName

data class BaseDTO(
    @SerializedName("user")
    val user: OwnerDTO,
    @SerializedName("repo")
    val repo: RepoDTO
)
