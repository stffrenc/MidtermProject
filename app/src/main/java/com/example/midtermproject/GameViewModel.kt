package com.example.midtermproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel : ViewModel() {

    val number = MutableLiveData<Int>()
    val guess = MutableLiveData<Int>()
    val attempts = MutableLiveData<Int>()
    val playerName = MutableLiveData<String>()
    private var diff = 0

    fun isDiffPositive() : Boolean{
        return diff > 0
    }

    fun checkGuess(): Boolean {
        if (guess.value == number.value) {
            return true
        } else {
            // Guess incorrect
            diff = guess.value.toString().toInt() - number.value.toString().toInt()
            return false
        }
    }

    fun increment() {
        val current = guess.value ?: 0
        val newValue = current + 1
        if(newValue <= 100) {
            guess.value = newValue
        }
    }

    fun decrement() {
        val current = guess.value ?: 0
        val decremented = current - 1
        if(decremented >= 0) {
            guess.value = decremented
        }
    }

    fun generateNumber() {
        number.value = (0..100).random()
    }



}