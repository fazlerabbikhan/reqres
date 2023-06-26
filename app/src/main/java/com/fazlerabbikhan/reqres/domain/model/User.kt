package com.fazlerabbikhan.reqres.domain.model

data class User(
    val id: Int,
    val avatar: String,
    val first_name: String,
    val last_name: String,
    val email: String
)