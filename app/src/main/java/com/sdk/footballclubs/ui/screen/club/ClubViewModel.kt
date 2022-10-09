package com.sdk.footballclubs.ui.screen.club

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sdk.footballclubs.network.ClubApi
import kotlinx.coroutines.launch

class ClubViewModel(
    private val api: ClubApi
) : ViewModel() {
    private val _state: MutableState<ClubState> = mutableStateOf(ClubState())
    val state: State<ClubState> get() = _state

    fun getClubById(id: Int) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                _state.value = _state.value.copy(
                    club = api.getClubById(id),
                    isLoading = false
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}