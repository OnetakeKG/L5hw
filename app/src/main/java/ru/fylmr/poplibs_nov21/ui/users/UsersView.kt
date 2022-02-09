package ru.fylmr.poplibs_nov21.ui.users

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip
import ru.fylmr.poplibs_nov21.model.GithubUserModel

interface UsersView : MvpView {

    @AddToEndSingle
    fun updateList(users: List<GithubUserModel>)

    @Skip
    fun showError(message: String?)
}