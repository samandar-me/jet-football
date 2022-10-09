package com.sdk.footballclubs.ui.screen.club

import com.sdk.footballclubs.data.Club

data class ClubState(
    val club: Club? = null,
    val isLoading: Boolean = false
)
