package com.nithesh.tapgame.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nithesh.tapgame.database.GameScoreDao

class GameViewModelFactory(
    private val userName: String,
    private val dataSource: GameScoreDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(userName, dataSource, application) as T
        }
        throw IllegalArgumentException("unknown viewmodel class")
    }
}