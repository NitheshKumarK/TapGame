package com.nithesh.tapgame.score

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nithesh.tapgame.database.GameScoreDao

class ScoreViewModel(
    val score: Int,
    private val database: GameScoreDao,
    application: Application
) : AndroidViewModel(application)