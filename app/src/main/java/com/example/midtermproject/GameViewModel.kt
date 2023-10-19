package com.example.midtermproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel : ViewModel() {
    val TAG = "GameViewModel"
    private val number = MutableLiveData<Int>()
    val guess = MutableLiveData(0)
    val attempts = MutableLiveData(0)
    val playerName = MutableLiveData<String>()
    private var diff = 0
    val attemptString = MutableLiveData<String>()

    fun isDiffPositive() : Boolean{
        return diff > 0
    }

    fun checkGuess(): Boolean {
        if (guess.value?.toInt() == number.value) {
            return true
        } else {
            // Guess incorrect
            diff = guess.value.toString().toInt() - number.value.toString().toInt()
            return false
        }
    }

    fun increment() {
        Log.d(TAG, "Increment function entered")
        val current = guess.value?.toInt() ?: 0
        Log.d("Current val", current.toString())
        val newValue = current + 1
        Log.d("New val", newValue.toString())
        if(newValue <= 100) {
            guess.value = newValue
            Log.d("New Guest Val", guess.value.toString())
        }
    }

    fun decrement() {
        Log.d(TAG, "Decrement function entered")
        val current = guess.value?.toInt() ?: 0
        Log.d("Current Value", current.toString())
        val decremented = current - 1
        Log.d("new Value", decremented.toString())
        if(decremented >= 0) {
            guess.value = decremented
            Log.d("New Guest Val", guess.value.toString())
        }
    }

    fun addAttempt(){
        val current = attempts.value ?: 0
        Log.d("Current Attempt Val", current.toString())
        val newVal = current?.plus(1)
        Log.d("New Attempt Val Pre Switch", newVal.toString())
        attempts.value = newVal
        Log.d("New Attempt Val", attempts.value.toString())
    }

    fun updateAttemptString(){
        val attemptsUp = attempts.value ?: 0
        Log.d("attemptsUp", attemptsUp.toString())
        val text = "Number of Attempts: ${attemptsUp.toString()}"
        Log.d("Text before log", text)
        attemptString.value = text
        Log.d("New Attempt String", attemptString.value?.toString() ?: "null")
    }


    fun generateNumber() {
        number.value = (0..100).random()
        Log.d("Number Value", number.value.toString())
    }

    fun updateGuess(newGuess: Int) {
        guess.value = newGuess
    }
    fun clearGuess() {
        guess.value = null
    }



}