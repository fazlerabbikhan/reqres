package com.fazlerabbikhan.reqres.domain.model

data class UserDetail(
    val id: Int,
    val avatar: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val supportUrl: String,
    val supportText: String
)
