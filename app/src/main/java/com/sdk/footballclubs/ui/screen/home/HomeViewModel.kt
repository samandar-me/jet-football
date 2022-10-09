package com.sdk.footballclubs.ui.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.footballclubs.network.ClubApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val api: ClubApi
) : ViewModel() {
    private val _state: MutableState<ClubListState> = mutableStateOf(ClubListState())
    val state: State<ClubListState> get() = _state

    init {
        getAllClubs()
    }

    fun getAllClubs() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            delay(3000)
            try {
                _state.value = _state.value.copy(
                    clubList = api.getAllClubs(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.stackTraceToString())
            }
        }
    }
}