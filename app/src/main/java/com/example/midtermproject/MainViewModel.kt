package com.example.midtermproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel(private val dao: ScoreDao) : ViewModel() {
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val playerName = MutableLiveData<String>()
    val playerScore = MutableLiveData<Int>()
    val playAgainText = MutableLiveData("")
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList


    fun updatePlayAgainText() {
        val name = playerName.value ?: ""
        val score = playerScore.value ?: 0
        val text = "${name.toString()} score: ${score.toString()}. \n\nPlay Another Game?"
        playAgainText.postValue(text)
    }

    fun onNavigatedToList() {
        _navigateToList.value = false
    }
    fun addScore(name: String, num: Int) {
        viewModelScope.launch {
            val score = Score()
            score.scoreName = name
            score.scoreNum = num
            dao.insert(score)
        }
    }



}