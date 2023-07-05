package com.fazlerabbikhan.reqres.presentation.ui.user_list

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fazlerabbikhan.reqres.R
import com.fazlerabbikhan.reqres.common.Constants
import com.fazlerabbikhan.reqres.databinding.UserListItemBinding
import com.fazlerabbikhan.reqres.domain.model.User
import com.fazlerabbikhan.reqres.presentation.ui.user_detail.UserDetailActivity

class UserListAdapter(private val context: Context) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

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
        val user = userList[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(context, UserDetailActivity::class.java)
            val userId = user.id.toString()
            intent.putExtra(Constants.PARAM_USER_ID, userId)
            context.startActivity(intent)
        }

        Glide.with(holder.itemView)
            .load(user.avatar)
            .fitCenter()
            .into(holder.itemView.findViewById(R.id.avatarImageView))
        holder.name.text = "${user.first_name} ${user.last_name}"
    }

    override fun getItemCount(): Int = userList.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(users: List<User>?) {
        userList = users ?: emptyList()
        notifyDataSetChanged()
    }
}
