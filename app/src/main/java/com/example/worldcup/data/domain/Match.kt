package com.example.worldcup.data.domain

import java.io.Serializable

data class Match(
   val year:Int,
   val stadium:String,
   val city:String,
   val homeTeamName:String,
   val awayTeamName:String,
   val homeTeamGoals:Int,
   val awayTeamGoals:Int,
   val refereeName:String,
) : Serializable