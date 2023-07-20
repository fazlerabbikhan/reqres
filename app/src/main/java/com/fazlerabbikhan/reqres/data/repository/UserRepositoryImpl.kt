package com.fazlerabbikhan.reqres.data.repository

import com.fazlerabbikhan.reqres.data.remote.ReqresApi
import com.fazlerabbikhan.reqres.data.remote.user_detail_dto.UserDetailDto
import com.fazlerabbikhan.reqres.data.remote.users_dto.UsersDto
import com.fazlerabbikhan.reqres.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: ReqresApi
) : UserRepository {

    override suspend fun getUsers(): UsersDto {
        return api.getUsers()
    }

    override suspend fun getUserById(userId: String): UserDetailDto {
        return api.getUserById(userId)
    }
}