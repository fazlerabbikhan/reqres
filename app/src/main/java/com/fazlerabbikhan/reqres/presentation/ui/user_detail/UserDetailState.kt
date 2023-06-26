package com.fazlerabbikhan.reqres.presentation.ui.user_detail

import com.fazlerabbikhan.reqres.domain.model.UserDetail

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: UserDetail? = null,
    val error: String = ""
)