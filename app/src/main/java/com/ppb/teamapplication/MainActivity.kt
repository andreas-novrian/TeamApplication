package com.ppb.teamapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ppb.teamapplication.view.TeamViewModel
import com.ppb.teamapplication.view.TeamViewModelFactory

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<TeamViewModel> {
        TeamViewModelFactory(
            (application as TeamApplication).localTeamRepository,
            (application as TeamApplication).remoteTeamRepository
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.teamsFromAPI.observe(this) { teamsResponse ->
            Log.d("MYTAG", teamsResponse.teams.toString())
        }
        viewModel.teamsFromDB.observe(this) {teams ->
            Log.d("MYTAGDB", teams.toString())
        }
        viewModel.getTeamsFromAPI()
    }
}