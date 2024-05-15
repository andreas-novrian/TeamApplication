package com.ppb.teamapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ppb.teamapplication.model.Team

@Database(entities = [Team::class], version = 1, exportSchema = false)
abstract class TeamDatabase : RoomDatabase() {
    abstract fun teamDAO() : TeamDAO

    companion object{
        @Volatile
        private var INSTANCE:TeamDatabase? = null

        fun getDatabase(context: Context): TeamDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamDatabase::class.java,
                    "application_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}