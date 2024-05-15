package com.ppb.teamapplication.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ppb.teamapplication.model.Team
import kotlinx.coroutines.flow.Flow


@Dao
interface TeamDAO {
    @Query("select * from team_table")
    fun getTeams(): Flow<List<Team>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(team: Team)

    @Delete
    suspend fun delete(team: Team)

}