package com.fazlerabbikhan.reqres.presentation.ui.user_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fazlerabbikhan.reqres.common.Constants
import com.fazlerabbikhan.reqres.common.Resource
import com.fazlerabbikhan.reqres.domain.use_case.GetUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData<UserDetailState>()
    val state: LiveData<UserDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_USER_ID)?.let { userId ->
            getUserDetail(userId)
        }
    }

    fun getUserDetail(userId: String) {
        getUserDetailUseCase(userId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = UserDetailState(user = result.data)
                }
                is Resource.Error -> {
                    _state.value = UserDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = UserDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}