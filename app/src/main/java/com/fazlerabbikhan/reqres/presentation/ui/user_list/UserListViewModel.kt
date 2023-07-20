package com.fazlerabbikhan.reqres.presentation.ui.user_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fazlerabbikhan.reqres.common.Resource
import com.fazlerabbikhan.reqres.domain.use_case.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UserListState>()
    val state: LiveData<UserListState> = _state

    init {
        getUsers()
    }

    private fun getUsers() {
        getUsersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("UserListViewModel", "${result.data}")
                    _state.value = UserListState(users = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = UserListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = UserListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}