package ru.fylmr.poplibs_nov21.domain

import io.reactivex.rxjava3.core.Single
import ru.fylmr.poplibs_nov21.model.GithubUserModel

interface IGithubUsersRepository {

    fun getUsers(): Single<List<GithubUserModel>>
}