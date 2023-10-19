package com.example.midtermproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val dao: ScoreDao) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create (modelClass: Class<T>) :T{
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }

}