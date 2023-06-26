package com.fazlerabbikhan.reqres.domain.use_case

import com.fazlerabbikhan.reqres.common.Resource
import com.fazlerabbikhan.reqres.data.remote.user_detail_dto.toUserDetail
import com.fazlerabbikhan.reqres.domain.model.UserDetail
import com.fazlerabbikhan.reqres.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userId: String): Flow<Resource<UserDetail>> = flow {
        try {
            emit(Resource.Loading<UserDetail>())
            val user = repository.getUserById(userId).toUserDetail()
            emit(Resource.Success<UserDetail>(user))
        } catch(e: HttpException) {
            emit(Resource.Error<UserDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<UserDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}