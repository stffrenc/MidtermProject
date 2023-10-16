package com.example.midtermproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.midtermproject.databinding.FragmentGuessBinding


class GuessFragment : Fragment() {
    private var _binding : FragmentGuessBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGuessBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel.generateNumber()
        binding.okButton.setOnClickListener {
            viewModel.attempts.value = viewModel.attempts.value!! + 1
            if(viewModel.checkGuess()) {
                val args = Bundle()
                val returnName = viewModel.playerName.value.toString()
                val returnScore = viewModel.attempts.value!!.toInt()
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

            }
        }
        binding.incrementButton.setOnClickListener{
            viewModel.increment()
        }
        binding.decrementButton.setOnClickListener{
            viewModel.guess.value = viewModel.guess.value!! - 1
        }
        binding.numGuess.addTextChangedListener {
            viewModel.decrement()
        }

        binding.playerName.addTextChangedListener {

        }
        return view
    }


}