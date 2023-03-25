package com.example.worldcup.ui


import android.view.LayoutInflater
import com.example.worldcup.data.domain.Match
import com.example.worldcup.databinding.ActivityDetailsBinding
import com.example.worldcup.util.Constants

class DetailsActivity: BaseActivity<ActivityDetailsBinding>() {

    override val LOG_TAG: String ="DETAILS_ACTIVITY"
    override val bindingInflater: (LayoutInflater) -> ActivityDetailsBinding =ActivityDetailsBinding::inflate
    override fun setup() {
       val match: Match?=intent.getSerializableExtra(Constants.Key.Match) as Match?
        match?.let {
            bindMatch(it)
        }
    }

    private fun bindMatch(match: Match) {
        binding?.apply {
            tvHomeTeam.text = match.homeTeamName
            tvAwayTeam.text = match.awayTeamName
            tvHomeGoals.text = match.homeTeamGoals.toString()
            tvAwayGoals.text = match.awayTeamGoals.toString()
            tvYear.text = match.year.toString()
        }
    }

    override fun addCallBacks() {

    }

}