package com.nithesh.tapgame.score

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nithesh.tapgame.database.GameScoreDao

class ScoreViewModelFactory(
    private val score: Int,
    private val dataSource: GameScoreDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScoreViewModel(score, dataSource, application) as T
        }
        throw IllegalArgumentException("unknown view model class")
    }
}