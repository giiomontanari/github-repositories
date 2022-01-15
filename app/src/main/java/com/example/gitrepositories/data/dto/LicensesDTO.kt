package com.example.gitrepositories.data.dto

import com.google.gson.annotations.SerializedName

data class LicensesDTO(
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("spdx_id")
    val spdxId: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("node_id")
    val nodeId: String
)
