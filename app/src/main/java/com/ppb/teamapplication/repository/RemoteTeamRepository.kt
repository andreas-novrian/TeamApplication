package com.ppb.teamapplication.repository

import com.ppb.teamapplication.api.ApiClient
import com.ppb.teamapplication.model.TeamResponse
import retrofit2.Response

class RemoteTeamRepository(private val apiClient : ApiClient) {
    suspend fun getTeams(league: String): Response<TeamResponse>{
        return apiClient.apiService.getTeams(league)
    }
}