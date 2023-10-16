package com.example.midtermproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.example.midtermproject.databinding.FragmentGameBinding



class GameFragment : FragmentActivity() {
    private val viewModel: GameViewModel by viewModels()
    private var  _binding : FragmentGameBinding? = null
    private val binding get() = _binding!!

    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val attemptsFragment = AttemptsFragment()
        val guessFragment = GuessFragment()
        fragmentTransaction.replace(R.id.attempt_container, attemptsFragment)
        fragmentTransaction.replace(R.id.guess_container, guessFragment)
        fragmentTransaction.commit()
        return binding.root
    }


}