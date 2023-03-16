package com.example.worldcup.ui

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import com.example.worldcup.data.DataManager
import com.example.worldcup.data.domain.Match
import com.example.worldcup.databinding.ActivityMainBinding
import com.example.worldcup.util.CsvParser
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate
    override val LOG_TAG: String = "MAIN_ACTIVITY"

    override fun setup() {
        parseFile()
    }

    private fun parseFile() {
        val inputStream = assets.open("WorldCupMatches.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CsvParser()
        buffer.forEachLine {
            val currentMatch = parser.parse(it)
            DataManager.addMatch(currentMatch)
        }
        bindMatch(DataManager.getCurrentMatch())
    }

    override fun addCallBacks() {
        binding?.apply {
            ivNext.setOnClickListener {
                bindMatch(DataManager.getNextMatch())
            }
            ivBack.setOnClickListener {
                bindMatch(DataManager.getPreviousMatch())
            }
        }
    }

    private fun bindMatch(match: Match) {
        binding?.apply {
            tvYear.text = match.year.toString()
            tvHomeTeam.text = match.homeTeamName
            tvAwayTeam.text = match.awayTeamName
            tvHomeGoals.text = match.homeTeamGoals.toString()
            tvAwayGoals.text = match.awayTeamGoals.toString()
            tvStadium.text = match.stadium

        }
    }
}
