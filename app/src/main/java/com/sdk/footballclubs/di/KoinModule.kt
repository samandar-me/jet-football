package com.sdk.footballclubs.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.sdk.footballclubs.network.ClubApi
import com.sdk.footballclubs.ui.screen.club.ClubViewModel
import com.sdk.footballclubs.ui.screen.home.HomeViewModel
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val myModule = module {
    single {
        provideClubApi()
    }
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        ClubViewModel(get())
    }
}

inline fun provideClubApi(): ClubApi {
    return Retrofit.Builder()
        .baseUrl("http://192.168.43.110:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ClubApi::class.java)
}
