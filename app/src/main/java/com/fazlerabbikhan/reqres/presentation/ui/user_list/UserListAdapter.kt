package com.fazlerabbikhan.reqres.presentation.ui.user_list

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.fazlerabbikhan.reqres.R
import com.fazlerabbikhan.reqres.common.Constants
import com.fazlerabbikhan.reqres.databinding.UserListItemBinding
import com.fazlerabbikhan.reqres.domain.model.User
import com.fazlerabbikhan.reqres.presentation.ui.user_detail.UserDetailActivity

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var userList: List<User> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList.getOrNull(position)
        user?.let {
            holder.bind(it)
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, UserDetailActivity::class.java)
                intent.putExtra(Constants.PARAM_USER_ID, user.id)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = userList.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(users: List<User>?) {
        userList = users ?: emptyList()
        Log.d("userList", "${users?.size}")
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            val context = binding.root.context
            Log.d("userAvatar", user.avatar)

            Glide.with(context)
                .load(user.avatar).placeholder(R.drawable.person_avatar)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.avatarImageView)

            binding.nameTextView.text = "${user.first_name} ${user.last_name}"
        }
    }
}
