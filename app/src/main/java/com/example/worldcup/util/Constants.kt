package com.example.worldcup.util

import android.service.autofill.FieldClassification.Match

object Constants {
    object ColumnIndex{
        const val YEAR=0
        const val STADIUM=3
        const val CITY=4
        const val HOME_TEAM_NAME=5
        const val AWAY_TEAM_NAME=8
        const val HOME_TEAM_GOALS=6
        const val AWAY_TEAM_GOALS=7
        const val REFEREE_NAME=13
    }
    object Key{
        const val Match ="MATCH"
    }
}