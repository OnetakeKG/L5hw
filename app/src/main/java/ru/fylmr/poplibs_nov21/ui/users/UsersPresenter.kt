package ru.fylmr.poplibs_nov21.ui.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.fylmr.poplibs_nov21.domain.IGithubUsersRepository
import ru.fylmr.poplibs_nov21.screens.UsersScreenInitParams

class UsersPresenter(
    private val router: Router,
    private val usersRepository: IGithubUsersRepository,
    private val initModel: UsersScreenInitParams,
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        loadData()
    }

    private fun loadData() {
        usersRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { users ->
                    viewState.updateList(users)
                }, {
                    viewState.showError(it.message)
                }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onUserClicked() {
        // todo
    }
}