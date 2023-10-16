package com.example.midtermproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoresViewModelFactory(private val scoreName: String, private val scoreNum: Int, private val dao: ScoreDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoresViewModel::class.java)) {
            return ScoresViewModel(scoreName, scoreNum, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
