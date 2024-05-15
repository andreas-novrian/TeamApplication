package com.ppb.teamapplication

import android.app.Application
import com.ppb.teamapplication.api.ApiClient
import com.ppb.teamapplication.db.TeamDatabase
import com.ppb.teamapplication.repository.LocalTeamRepository
import com.ppb.teamapplication.repository.RemoteTeamRepository

class TeamApplication : Application() {
    val database by lazy { TeamDatabase.getDatabase(this) }
    val localTeamRepository by lazy { LocalTeamRepository(database.teamDAO()) }
    val remoteTeamRepository by lazy { RemoteTeamRepository(ApiClient) }
}