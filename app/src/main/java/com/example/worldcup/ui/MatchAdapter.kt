package com.example.worldcup.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcup.R
import com.example.worldcup.data.domain.Match
import com.example.worldcup.databinding.ItemMatchBinding


class MatchAdapter(private var list: List<Match>, private val listener: MatchInteractionListener) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

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
            tvHomeTeam.setOnClickListener { listener.onClickTeamName(currentMatch.homeTeamName) }
            tvAwayTeam.setOnClickListener { listener.onClickTeamName(currentMatch.awayTeamName) }
            root.setOnClickListener { listener.onClickItem(currentMatch) }
        }
    }

    fun setData(newList: List<Match>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size

    class MatchViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemMatchBinding.bind(viewItem)
    }
}