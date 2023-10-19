package com.example.midtermproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.media.MediaPlayer
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.midtermproject.databinding.FragmentGuessBinding


class GuessFragment : Fragment() {
    private var _binding : FragmentGuessBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by viewModels()
    private lateinit var incorrectBuzz: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val TAG = "GuessFragment"
        _binding = FragmentGuessBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel.generateNumber()
        incorrectBuzz = MediaPlayer.create(context, R.raw.buzz)
        incorrectBuzz.stop()
        incorrectBuzz.prepare()
        binding.okButton.setOnClickListener {
            viewModel.addAttempt()
            viewModel.updateAttemptString()
            if(viewModel.checkGuess()) {
                val args = Bundle()
                val returnName = viewModel.playerName.value.toString()
                Log.d("Return Name Val before nav", returnName)
                val returnScore = viewModel.attempts.value!!.toInt()
                Log.d("Return Score Val before nav", returnScore.toString())
                args.putString("playerName", returnName)
                args.putInt("playerScore", returnScore)
                findNavController().navigate(R.id.action_gameFragment_to_mainFragment, args)
            } else{
                if(viewModel.isDiffPositive()){
                    Toast.makeText(requireContext(),
                        "Your Guess is too high", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(),
                        "Your Guess is too low", Toast.LENGTH_SHORT).show()
                }
                if(incorrectBuzz.currentPosition != -1) incorrectBuzz.start()
                else{
                    incorrectBuzz.stop()
                    incorrectBuzz.prepare()
                    incorrectBuzz.start()
                }
            }
        }
        binding.numGuess.setText(viewModel.guess.value.toString())
        binding.incrementButton.setOnClickListener{
            Log.d(TAG, "Increment Button Pressed")
            viewModel.increment()
            binding.numGuess.setText(viewModel.guess.value.toString())
        }
        binding.decrementButton.setOnClickListener{
            Log.d(TAG, "Decrement Button Pressed")
            viewModel.decrement()
            binding.numGuess.setText(viewModel.guess.value.toString())
        }
        binding.numGuess.addTextChangedListener {
            val input = it.toString().trim()
            if(input.isNotEmpty()) {
                val guess = input.toInt()
                viewModel.updateGuess(guess)
            } else {
                viewModel.clearGuess() // Reset to default
            }

        }

        binding.playerName.addTextChangedListener {
            viewModel.playerName.value = it.toString()
        }
        return view
    }


}