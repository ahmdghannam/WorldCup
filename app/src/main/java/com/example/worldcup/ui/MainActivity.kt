package com.example.worldcup.ui

import android.content.Intent
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.worldcup.data.DataManager
import com.example.worldcup.data.domain.Match
import com.example.worldcup.databinding.ActivityMainBinding
import com.example.worldcup.util.Constants
import com.example.worldcup.util.CsvParser
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : BaseActivity<ActivityMainBinding>(), MatchInteractionListener {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate
    override val LOG_TAG: String = "MAIN_ACTIVITY"

    private lateinit var adapter: MatchAdapter
    override fun setup() {
        parseFile()
        adapter = MatchAdapter(DataManager.matches, this)
        binding?.recyclerMatch?.adapter = adapter
    }

    override fun addCallBacks() {
        binding?.fab?.setOnClickListener {
            addFinalMatch()
        }

    }

    private fun addFinalMatch() {
        val finalMatch = Match(
            year = 2022,
            stadium = "Lusail",
            city = "doha",
            homeTeamName = "argentina",
            awayTeamName = "france",
            homeTeamGoals = 3,
            awayTeamGoals = 3,
            refereeName = "mamdoh"
        )
        DataManager.addMatchAt(0,finalMatch)
        adapter.setData(DataManager.matches)
    }

    private fun parseFile() {
        val inputStream = assets.open("WorldCupMatches.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CsvParser()
        buffer.forEachLine {
            val currentMatch = parser.parse(it)
            DataManager.addMatch(currentMatch)
        }
    }

    override fun onClickItem(match: Match) {
        val myIntent = Intent(this, DetailsActivity::class.java)
        myIntent.putExtra(Constants.Key.Match, match)
        startActivity(myIntent)
    }

    override fun onClickTeamName(name: String) {
        Toast.makeText(applicationContext, name, Toast.LENGTH_SHORT).show()
    }

    override fun deleteItemAt(index: Int) {
        val currentMatch=DataManager.matches[index]
        Log.i("Recycler", "deleteItemAt: index =$index, current match = $currentMatch ,")
        DataManager.deleteMatchAt(index)
        adapter.setData(DataManager.matches)
    }

}




