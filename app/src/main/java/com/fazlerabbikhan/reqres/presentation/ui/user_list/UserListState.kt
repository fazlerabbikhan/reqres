package com.fazlerabbikhan.reqres.presentation.ui.user_list

import com.fazlerabbikhan.reqres.domain.model.User

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = ""
)
