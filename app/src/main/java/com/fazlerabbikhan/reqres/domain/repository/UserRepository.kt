package com.fazlerabbikhan.reqres.domain.repository

import com.fazlerabbikhan.reqres.data.remote.user_detail_dto.UserDetailDto
import com.fazlerabbikhan.reqres.data.remote.users_dto.UsersDto

interface UserRepository {

    suspend fun getUsers(): UsersDto

    suspend fun getUserById(userId: String): UserDetailDto
}