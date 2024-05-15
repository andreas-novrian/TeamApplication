package com.ppb.teamapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ppb.teamapplication.databinding.ItemTeamBinding
import com.ppb.teamapplication.model.Team


class TeamAdapter(private val isFromApi : Boolean) :
RecyclerView.Adapter<TeamAdapter.ViewHolder>(){

    var teams = emptyList<Team>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.ViewHolder {
        val binding = ItemTeamBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamAdapter.ViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    inner class ViewHolder(private val binding: ItemTeamBinding) :
            RecyclerView.ViewHolder(binding.root){
                fun bind(team: Team) {
                    binding.tvName.text = team.strTeam
                    Glide.with(binding.root).load(team.strTeamBadge).fitCenter().into(binding.ivLogo)
                    if (isFromApi){
                        binding.fabSave.visibility = View.VISIBLE
                        binding.fabDelete.visibility = View.GONE
                    } else {
                        binding.fabSave.visibility = View.GONE
                        binding.fabDelete.visibility = View.VISIBLE
                    }
                    binding.fabSave.setOnClickListener {
                        onButtonClickListener?.let {
                            it(team)
                        }
                    }
                    binding.fabDelete.setOnClickListener {
                        onButtonClickListener?.let {
                            it(team)
                        }
                    }

                }

            }
    private var onButtonClickListener: ((Team) -> Unit)? = null
    fun setOnButtonClickListener(listener: (Team) -> Unit) {
        onButtonClickListener = listener
    }
}