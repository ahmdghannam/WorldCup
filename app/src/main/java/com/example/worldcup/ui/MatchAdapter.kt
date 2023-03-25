package com.example.worldcup.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcup.R
import com.example.worldcup.data.domain.Match
import com.example.worldcup.databinding.ItemMatchBinding

class MatchAdapter(val list: List<Match>) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val currentMatch = list[position]
        holder.binding.apply {
            tvHomeTeam.text = currentMatch.homeTeamName
            tvAwayTeam.text = currentMatch.awayTeamName
            tvHomeGoals.text = currentMatch.homeTeamGoals.toString()
            tvAwayGoals.text = currentMatch.awayTeamGoals.toString()
            tvYear.text = currentMatch.year.toString()
            if (currentMatch.homeTeamGoals > currentMatch.awayTeamGoals) {
                tvHomeGoals.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.green))
                tvAwayGoals.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.country_color))
            }
            else if (currentMatch.homeTeamGoals < currentMatch.awayTeamGoals){
                tvHomeGoals.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.country_color))
                tvAwayGoals.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.green))
            }
            else{
                tvHomeGoals.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.country_color))
                tvAwayGoals.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.country_color))
            }

        }
    }

    override fun getItemCount() = list.size

    class MatchViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemMatchBinding.bind(viewItem)
    }
}