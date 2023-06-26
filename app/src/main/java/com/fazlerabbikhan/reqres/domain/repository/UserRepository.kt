package com.fazlerabbikhan.reqres.domain.repository

import com.fazlerabbikhan.reqres.data.remote.user_detail_dto.UserDetailDto
import com.fazlerabbikhan.reqres.data.remote.user_dto.UserDto

interface UserRepository {

    suspend fun getUsers(): UserDto

    suspend fun getUserById(userId: String): UserDetailDto
}