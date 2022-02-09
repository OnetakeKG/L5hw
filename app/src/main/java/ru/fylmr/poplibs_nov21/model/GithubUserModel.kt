package ru.fylmr.poplibs_nov21.model

import com.google.gson.annotations.Expose

data class GithubUserModel(
    @Expose
    val id: Long,

    @Expose
    val login: String,

    @Expose
    val avatarUrl: String? = null,
)