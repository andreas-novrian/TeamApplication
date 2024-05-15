package com.ppb.teamapplication.repository

import com.ppb.teamapplication.db.TeamDAO
import com.ppb.teamapplication.model.Team
import kotlinx.coroutines.flow.Flow

class LocalTeamRepository(private val teamDAO: TeamDAO) {
    val teams : Flow<List<Team>> = teamDAO.getTeams()

    suspend fun insert(team: Team){
        teamDAO.insert(team)
    }
    suspend fun delete(team: Team) {
        teamDAO.delete(team)
    }
}