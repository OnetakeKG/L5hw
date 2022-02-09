package ru.fylmr.poplibs_nov21.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.fylmr.poplibs_nov21.App
import ru.fylmr.poplibs_nov21.databinding.FragmentUsersBinding
import ru.fylmr.poplibs_nov21.domain.GithubUsersRepository
import ru.fylmr.poplibs_nov21.model.GithubUserModel
import ru.fylmr.poplibs_nov21.network.ApiHolder
import ru.fylmr.poplibs_nov21.screens.UsersScreenInitParams
import ru.fylmr.poplibs_nov21.ui.base.BackButtonListener
import ru.fylmr.poplibs_nov21.ui.base.GlideImageLoader
import ru.fylmr.poplibs_nov21.ui.users.adapter.UsersAdapter

class UsersFragment() : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private val presenter by moxyPresenter {
        UsersPresenter(
            App.instance.router,
            GithubUsersRepository(ApiHolder.githubApiService),
            initModel
        )
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    private val adapter by lazy {
        UsersAdapter(GlideImageLoader()) { presenter.onUserClicked() }
    }

    private val initModel by lazy {
        requireArguments().getSerializable(KEY_INIT_PARAMS) as UsersScreenInitParams
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.usersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.usersRecycler.adapter = adapter
    }

    override fun updateList(users: List<GithubUserModel>) {
        adapter.submitList(users)
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun showError(message: String?) {
        Toast.makeText(requireContext(), message.orEmpty(), Toast.LENGTH_SHORT)
            .show()
    }

    companion object {

        private const val KEY_INIT_PARAMS = "KEY_INIT_PARAMS"

        fun newInstance(initModel: UsersScreenInitParams): UsersFragment {
            return UsersFragment().apply {
                arguments = bundleOf(KEY_INIT_PARAMS to initModel)
            }
        }
    }
}