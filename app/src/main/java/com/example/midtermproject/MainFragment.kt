package com.example.midtermproject

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.example.midtermproject.databinding.FragmentMainBinding
import com.example.midtermproject.databinding.FragmentScoresBinding


class MainFragment : Fragment() {
    val TAG = "MainFragment"
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val orientation = resources.configuration.orientation
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            _binding = FragmentMainBinding.inflate(inflater, container, false)
        } else{
            _binding = FragmentMainBinding.inflate(inflater,container,false)
        }
        val application = requireNotNull(this.activity).application
        val dao = ScoreDatabase.getInstance(application).scoreDao
        val viewModelFactory = MainViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val args = requireArguments()
        val playerName = args?.getString("playerName") ?: ""
        val playerScore = args?.getInt("playerScore") ?: 0
        if(playerName != "" && playerScore != 0){
            viewModel.addScore(playerName, playerScore)
            viewModel.playerName.value = playerName
            Log.d("Last Player Name", viewModel.playerName.value.toString())
            viewModel.playerScore.value = playerScore
            Log.d("Last Player Score", viewModel.playerScore.value.toString())
            viewModel.updatePlayAgainText()
            binding.mainText.text = viewModel.playAgainText.toString()
            Log.d(TAG, viewModel.playAgainText.toString())
            binding.mainText.isVisible = true
        }


        else{
            binding.mainText.isVisible = false
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.playButton.setOnClickListener {
            Log.v(TAG, "Play Button Pressed")
            view?.findNavController()
                ?.navigate(R.id.action_mainFragment_to_gameFragment)
        }
        binding.highScoreButton.setOnClickListener {
            Log.v(TAG, "High Score Button Pressed")
            view?.findNavController()
                ?.navigate(R.id.action_mainFragment_to_scoresFragment)
        }
        return binding.root
    }



}