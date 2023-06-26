package com.fazlerabbikhan.reqres.data.remote.user_detail_dto

import com.fazlerabbikhan.reqres.domain.model.UserDetail

data class UserDetailDto(
    val data: Data,
    val support: Support
)

fun UserDetailDto.toUserDetail(): UserDetail {
    return UserDetail(
        id = data.id,
        avatar = data.avatar,
        first_name = data.first_name,
        last_name = data.last_name,
        email = data.email,
        supportUrl = support.url,
        supportText = support.text
    )
}