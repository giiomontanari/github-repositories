package com.example.gitrepositories.data.dto

import com.google.gson.annotations.SerializedName

data class RepoDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: OwnerDTO
)
