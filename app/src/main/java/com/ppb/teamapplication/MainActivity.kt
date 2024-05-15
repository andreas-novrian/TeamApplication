package com.ppb.teamapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ppb.teamapplication.databinding.ActivityMainBinding
import com.ppb.teamapplication.view.TeamViewModel
import com.ppb.teamapplication.view.TeamViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHost = supportFragmentManager
            .findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val navController = navHost.navController
        binding.bnvTeam.setupWithNavController(navController)
    }
}