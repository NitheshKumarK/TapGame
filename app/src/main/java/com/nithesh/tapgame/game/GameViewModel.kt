package com.nithesh.tapgame.game

import android.app.Application
import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.*
import com.nithesh.tapgame.database.GameScore
import com.nithesh.tapgame.database.GameScoreDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel(
    val userName: String,
    private val database: GameScoreDao,
    application: Application
) : AndroidViewModel(application) {

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private var _buttonName = MutableLiveData<String>()
    val buttonName: LiveData<String>
        get() = _buttonName

    private var _time = MutableLiveData<Long>()


    val timeString: LiveData<String> = Transformations.map(_time) {
        DateUtils.formatElapsedTime(it)
    }

    private var _isGameCompleted = MutableLiveData<Boolean>()
    val isGameCompleted: LiveData<Boolean>
        get() = _isGameCompleted

    private lateinit var currentGameScore: GameScore

    init {
        _score.value = 0
        _buttonName.value = "START"
        _isGameCompleted.value = false
        currentGameScore = GameScore(userName = userName)
        viewModelScope.launch { insert(currentGameScore) }

    }

    fun increaseScore() {
        if (_buttonName.value.equals("TAP")) {
            _score.value = _score.value!!.plus(1)
        } else {
            miniTimer.start()
        }
    }

    fun changeButtonName() {
        _buttonName.value = "TAP"
    }

    fun onGameFinish() {
        _isGameCompleted.value = null
    }

    private suspend fun insert(gameScore: GameScore) {
        withContext(Dispatchers.IO) {
            database.insertGame(gameScore)
        }
    }


    private val miniTimer = object : CountDownTimer(MINI_COUNT_DOWN_TIME, ONE_SECOND) {
        override fun onTick(millisUntilFinished: Long) {
            _time.value = (millisUntilFinished / ONE_SECOND)
        }

        override fun onFinish() {
            changeButtonName()
            timer.start()
        }

    }

    private val timer = object : CountDownTimer(COUNT_DOWN_TIME, ONE_SECOND) {
        override fun onTick(millisUntilFinished: Long) {
            _time.value = (millisUntilFinished / ONE_SECOND)
        }

        override fun onFinish() {
            _isGameCompleted.value = true
        }
    }

    companion object TimerVariables {
        private const val MINI_COUNT_DOWN_TIME = 4000L
        private const val COUNT_DOWN_TIME = 10000L
        private const val ONE_SECOND = 1000L
    }
}