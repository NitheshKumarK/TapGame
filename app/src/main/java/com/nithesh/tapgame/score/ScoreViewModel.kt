package com.nithesh.tapgame.score

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nithesh.tapgame.database.GameScore
import com.nithesh.tapgame.database.GameScoreDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScoreViewModel(
    var score: Int,
    private val database: GameScoreDao,
    application: Application
) : AndroidViewModel(application) {
    init {

        viewModelScope.launch {
            val gameScore = getLastUser()
            gameScore!!.score = score
            update(gameScore)
        }
    }

    fun deleteTable() {
        viewModelScope.launch {
            delete()
            score = 0
        }
    }

    private suspend fun delete() {
        withContext(Dispatchers.IO) {
            val gameUser = database.getLastUser()
            gameUser!!.gameScoreId = 0
            database.deleteAllGame()
        }
    }

    private suspend fun update(gameScore: GameScore) {
        withContext(Dispatchers.IO) {
            database.updateGame(gameScore)
        }
    }

    private suspend fun getLastUser(): GameScore? {
        val gameScore: GameScore?
        withContext(Dispatchers.IO) {
            gameScore = database.getLastUser()
        }
        return gameScore
    }
}