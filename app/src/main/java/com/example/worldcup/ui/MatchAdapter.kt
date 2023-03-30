package com.example.worldcup.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcup.R
import com.example.worldcup.data.domain.Match
import com.example.worldcup.databinding.ItemMatchBinding
import com.example.worldcup.databinding.ItemMatchesHeaderBinding


class MatchAdapter(private var list: List<Match>, private val listener: MatchInteractionListener) :
    RecyclerView.Adapter<MatchAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
       return when(viewType){
            VIEW_TYPE_HEADER ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_matches_header,parent,false)
                 HeaderViewHolder(view)
            }
            VIEW_TYPE_MATCH -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_match, parent, false)
             MatchViewHolder(view)
            }
            else -> super.createViewHolder(parent,viewType)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentMatch = list[position]
        when(holder){
            is MatchViewHolder ->{
                bindMatch(holder, currentMatch)
            }
            else -> {}
        }

    }

    private fun bindMatch(
        holder: MatchViewHolder,
        currentMatch: Match
    ) {
        holder.binding.apply {
            tvHomeTeam.text = currentMatch.homeTeamName
            tvAwayTeam.text = currentMatch.awayTeamName
            tvHomeGoals.text = currentMatch.homeTeamGoals.toString()
            tvAwayGoals.text = currentMatch.awayTeamGoals.toString()
            tvYear.text = currentMatch.year.toString()
            tvHomeTeam.setOnClickListener { listener.onClickTeamName(currentMatch.homeTeamName) }
            tvAwayTeam.setOnClickListener { listener.onClickTeamName(currentMatch.awayTeamName) }
            root.setOnClickListener { listener.onClickItem(currentMatch) }
            iconDelete.setOnClickListener {
                val currentPosition = holder.adapterPosition
                listener.deleteItemAt(currentPosition)
            }
        }
    }


    fun setData(newList: List<Match>) {
        val diffResult = DiffUtil.calculateDiff(MatchDifferentUtility(list, newList))
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }


    override fun getItemViewType(position: Int): Int {
        return if (position==0) VIEW_TYPE_HEADER
        else VIEW_TYPE_MATCH
    }
    override fun getItemCount() = list.size

    sealed class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)

    class MatchViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemMatchBinding.bind(viewItem)
    }

    class HeaderViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemMatchesHeaderBinding.bind(viewItem)
    }

    companion object{
        const val VIEW_TYPE_HEADER=11
        const val VIEW_TYPE_MATCH=12
    }
}