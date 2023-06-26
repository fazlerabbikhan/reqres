package com.fazlerabbikhan.reqres.data.remote

import com.fazlerabbikhan.reqres.data.remote.user_detail_dto.UserDetailDto
import com.fazlerabbikhan.reqres.data.remote.user_dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ReqresApi {

    @GET("/api/users?per_page=12")
    suspend fun getUsers(): UserDto

    @GET("/api/users/{userId}")
    suspend fun getUserById(@Path("userId") userId: String): UserDetailDto
}