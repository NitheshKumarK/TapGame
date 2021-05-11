package com.nithesh.tapgame.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoreViewModelFactory(private val score: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScoreViewModel(score) as T
        }
        throw IllegalArgumentException("unknown view model class")
    }
}