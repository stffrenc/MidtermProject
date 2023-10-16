package com.example.midtermproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ScoresViewModel(private val scoreName: String, private val scoreNum: Int, private val dao: ScoreDao) : ViewModel() {
    val scores = dao.getAll()
    private val _navigateToScore = MutableLiveData<Long?>()
    val navigateToScore: LiveData<Long?>
        get() = _navigateToScore

    fun deleteScore(scoreId: Long){
        viewModelScope.launch{
            val score = Score()
            score.scoreId = scoreId
            dao.delete(score)
        }
    }
    fun onScoreClicked(scoreId: Long) {
        _navigateToScore.value = scoreId
    }
    fun onNoteNavigated() {
        _navigateToScore.value = null
    }
}