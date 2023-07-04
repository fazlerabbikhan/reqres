package com.fazlerabbikhan.reqres.presentation.ui.user_list

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

    class UserViewHolder(binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = itemView.findViewById(R.id.nameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList.getOrNull(position)

        user?.let {
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, UserDetailActivity::class.java)
                intent.putExtra(Constants.PARAM_USER_ID, user.id)
                holder.itemView.context.startActivity(intent)
            }

            Glide
                .with(holder.itemView.context)
                .load(user.avatar)
                .fitCenter()
                .into(holder.itemView.findViewById(R.id.avatarImageView))
            holder.name.text = "${user.first_name} ${user.last_name}"
        }
    }

    override fun getItemCount(): Int = userList.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(users: List<User>?) {
        userList = users ?: emptyList()
        Log.d("userList", "${users?.size}")
        notifyDataSetChanged()
    }
}
