package com.ppb.teamapplication.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ppb.teamapplication.model.Team
import com.ppb.teamapplication.model.TeamResponse
import com.ppb.teamapplication.repository.LocalTeamRepository
import com.ppb.teamapplication.repository.RemoteTeamRepository
import kotlinx.coroutines.launch

class TeamViewModel(
    private val localTeamRepository: LocalTeamRepository,
    private val remoteTeamRepository: RemoteTeamRepository
) : ViewModel() {
    val teamsFromAPI = MutableLiveData<TeamResponse>()
    val teamsFromDB = localTeamRepository.teams.asLiveData()
    val isLoading = MutableLiveData<Boolean>()
    private val LEAGUE = "NASCAR Cup Series"

    fun insertToDB(team: Team) = viewModelScope.launch {
        isLoading.postValue(true)
        localTeamRepository.insert(team)
        isLoading.postValue(false)
    }

    fun deleteFromDB(team: Team) = viewModelScope.launch {
        isLoading.postValue(true)
        localTeamRepository.delete(team)
        isLoading.postValue(false)
    }

    fun getTeamsFromAPI() {
        isLoading.postValue(true)
        viewModelScope.launch {
            val response = remoteTeamRepository.getTeams(LEAGUE)
            if (response.isSuccessful) {
                teamsFromAPI.postValue(response.body())
            }
            isLoading.postValue(false)
        }
    }
}

class TeamViewModelFactory(
    private val localTeamRepository: LocalTeamRepository,
    private val remoteTeamRepository: RemoteTeamRepository
) : ViewModelProvider.Factory{
    // Ctrl+o
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return  TeamViewModel(localTeamRepository, remoteTeamRepository) as T
        }
        throw IllegalArgumentException("kelas ViewModel Tidak dikenali")
    }
}