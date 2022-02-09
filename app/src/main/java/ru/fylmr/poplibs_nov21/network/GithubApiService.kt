package ru.fylmr.poplibs_nov21.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.fylmr.poplibs_nov21.model.GithubUserModel

interface GithubApiService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUserModel>>



}