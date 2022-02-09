package ru.fylmr.poplibs_nov21.ui.users.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.fylmr.poplibs_nov21.databinding.ItemUserBinding
import ru.fylmr.poplibs_nov21.model.GithubUserModel
import ru.fylmr.poplibs_nov21.ui.base.ImageLoader

class UsersAdapter(
    private val imageLoader: ImageLoader<ImageView>,
    private val itemClickListener: (GithubUserModel) -> Unit,
) : ListAdapter<GithubUserModel, UsersAdapter.UserViewHolder>(GithubUserItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.showUser(currentList[position])
    }

    inner class UserViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root) {

        fun showUser(githubUserModel: GithubUserModel) {
            vb.root.setOnClickListener { itemClickListener(githubUserModel) }
            vb.tvLogin.text = githubUserModel.login
            itemView.setOnClickListener {
                Log.d("@@", "here")
            }

            if (githubUserModel.avatarUrl != null) {
                imageLoader.loadInto(githubUserModel.avatarUrl, vb.avatarImageView)
            }
        }
    }
}

object GithubUserItemCallback : DiffUtil.ItemCallback<GithubUserModel>() {

    override fun areItemsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GithubUserModel, newItem: GithubUserModel): Boolean {
        return oldItem == newItem
    }
}