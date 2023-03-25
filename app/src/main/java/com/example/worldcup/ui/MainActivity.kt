package com.example.worldcup.ui

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import com.example.worldcup.data.DataManager
import com.example.worldcup.data.domain.Match
import com.example.worldcup.databinding.ActivityMainBinding
import com.example.worldcup.util.Constants
import com.example.worldcup.util.CsvParser
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : BaseActivity<ActivityMainBinding>() ,MatchInteractionListener {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate
    override val LOG_TAG: String = "MAIN_ACTIVITY"

    override fun setup() {
        parseFile()
        val adapter = MatchAdapter(DataManager.matches.reversed(),this)
        binding?.recyclerMatch?.adapter = adapter
    }

    override fun addCallBacks() {

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
        val myIntent= Intent(this,DetailsActivity::class.java)
        myIntent.putExtra(Constants.Key.Match,match)
        startActivity(myIntent)
    }

    override fun onClickTeamName(name: String) {
        Toast.makeText(applicationContext, name, Toast.LENGTH_SHORT).show()
    }

}




