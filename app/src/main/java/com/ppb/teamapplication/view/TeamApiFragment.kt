package com.ppb.teamapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ppb.teamapplication.R
import com.ppb.teamapplication.TeamApplication
import com.ppb.teamapplication.databinding.FragmentTeamBinding


class TeamApiFragment : Fragment() {
    private lateinit var binding: FragmentTeamBinding
    private val viewModel: TeamViewModel by viewModels {
        TeamViewModelFactory(
            (activity?.application as TeamApplication).localTeamRepository,
            (activity?.application as TeamApplication).remoteTeamRepository
        )
    }
    private val adapter = TeamAdapter(true)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeamBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTeam.layoutManager = LinearLayoutManager(activity)
        binding.rvTeam.adapter = adapter
        viewModel.teamsFromAPI.observe(viewLifecycleOwner) { response ->
            adapter.teams = response.teams
        }
        viewModel.getTeamsFromAPI()
        adapter.setOnButtonClickListener { team ->
            viewModel.insertToDB(team)
        }
    }
}