package com.sdk.footballclubs.ui.screen.home

import com.sdk.footballclubs.data.Club

data class ClubListState(
    val clubList: List<Club>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)