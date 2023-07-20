package com.fazlerabbikhan.reqres.data.remote.users_dto

import com.fazlerabbikhan.reqres.domain.model.User

data class UsersDto(
    val data: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)

fun UsersDto.toUsers(): List<User> {
    return data.map { user ->
        User(
            id = user.id,
            avatar = user.avatar,
            first_name = user.first_name,
            last_name = user.last_name,
            email = user.email
        )
    }
}