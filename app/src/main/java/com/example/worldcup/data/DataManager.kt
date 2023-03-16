package com.example.worldcup.data

import com.example.worldcup.data.domain.Match

object DataManager {
    private val matchesList = mutableListOf<Match>()
    private var matchIndex = 0
    fun addMatch(match: Match) {
        matchesList.add(match)
    }

    fun getCurrentMatch() = matchesList[matchIndex]

    fun getNextMatch(): Match {
        matchIndex++
        if (matchIndex > matchesList.lastIndex) {
            matchIndex = 0
        }
        return matchesList[matchIndex]
    }

    fun getPreviousMatch(): Match {
        matchIndex--
        if (matchIndex < 0) {
            matchIndex = matchesList.lastIndex
        }
        return matchesList[matchIndex]
    }
}