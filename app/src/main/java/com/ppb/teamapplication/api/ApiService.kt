package com.ppb.teamapplication.api

import com.ppb.teamapplication.model.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("search_all_teams.php")
    suspend fun getTeams(
        @Query("l") league : String
    ) : Response<TeamResponse>
}