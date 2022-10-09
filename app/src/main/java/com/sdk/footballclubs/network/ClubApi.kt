package com.sdk.footballclubs.network

import com.sdk.footballclubs.data.Club
import retrofit2.http.GET
import retrofit2.http.Path

interface ClubApi {

    @GET("clublist")
    suspend fun getAllClubs(): List<Club>

    @GET("clublist/{id}")
    suspend fun getClubById(@Path("id") id: Int): Club

}