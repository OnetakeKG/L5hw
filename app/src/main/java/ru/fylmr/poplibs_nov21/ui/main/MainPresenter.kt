package ru.fylmr.poplibs_nov21.ui.main

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.fylmr.poplibs_nov21.screens.AppScreens
import ru.fylmr.poplibs_nov21.screens.UsersScreenInitParams

class MainPresenter(
    private val router: Router,
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(AppScreens.usersScreen(UsersScreenInitParams("")))
    }

    fun backPressed() {
        router.exit()
    }
}