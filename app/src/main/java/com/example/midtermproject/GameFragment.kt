package com.example.midtermproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.example.midtermproject.databinding.FragmentGameBinding
import kotlin.math.log


class GameFragment : Fragment() {
    val TAG = "GameFragment"
    private val viewModel: GameViewModel by viewModels()
    private var  _binding : FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        Log.v(TAG, "GameFragment entered")
        val fragmentTransaction = childFragmentManager.beginTransaction()
        val attemptsFragment = AttemptsFragment()
        val guessFragment = GuessFragment()
        fragmentTransaction.replace(R.id.attempt_container, attemptsFragment)
        fragmentTransaction.replace(R.id.guess_container, guessFragment)
        fragmentTransaction.commit()
        Log.v(TAG, "Fragment Transaction occurs")
        return binding.root
    }


}