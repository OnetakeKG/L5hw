package ru.fylmr.poplibs_nov21.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.fylmr.poplibs_nov21.ui.users.UsersFragment
import java.io.Serializable

object AppScreens {

    fun usersScreen(initModel: UsersScreenInitParams) = FragmentScreen {
        UsersFragment.newInstance(initModel)
    }
}

data class UsersScreenInitParams(
    val exampleField: String = ""
) : Serializable