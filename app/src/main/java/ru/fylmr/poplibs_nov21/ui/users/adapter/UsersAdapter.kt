package ru.fylmr.poplibs_nov21.ui.users.adapter

import android.app.Activity
import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.fylmr.poplibs_nov21.databinding.ItemUserBinding
import ru.fylmr.poplibs_nov21.model.GithubUserModel
import ru.fylmr.poplibs_nov21.network.ApiHolder
import ru.fylmr.poplibs_nov21.ui.base.ImageLoader
import ru.fylmr.poplibs_nov21.ui.main.MainActivity
import kotlin.coroutines.coroutineContext

import retrofit2.Response


class UsersAdapter(
    private val imageLoader: ImageLoader<ImageView>,
    private val itemClickListener: (GithubUserModel) -> Unit,
) : APIService, ListAdapter<GithubUserModel, UsersAdapter.UserViewHolder>(GithubUserItemCallback)  {

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
                val dialog = AlertDialog.Builder(itemView.context)
                dialog.setTitle(githubUserModel.reposUrl)
                dialog.show()
                Log.d("@@", "here")



             val retrofit =   Retrofit.Builder()
                    .baseUrl(githubUserModel.reposUrl.toString() + "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service: APIService = retrofit.create(APIService::class.java)
                val response: List<String> = service.getName("")
            }

            if (githubUserModel.avatarUrl != null) {
                imageLoader.loadInto(githubUserModel.avatarUrl, vb.avatarImageView)
            }
        }
    }

    override fun getName(url: String?): List<String> {
        TODO("Not yet implemented")
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