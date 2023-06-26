package com.fazlerabbikhan.reqres.data.remote.user_dto

import com.fazlerabbikhan.reqres.domain.model.User

data class Data(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
)

fun Data.toUser(): User {
    return User(
        id = id,
        avatar = avatar,
        first_name = first_name,
        last_name = last_name,
        email = email
    )
}