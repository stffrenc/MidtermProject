package com.example.midtermproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.example.midtermproject.databinding.FragmentAttemptsBinding
import com.example.midtermproject.databinding.FragmentGameBinding



class AttemptsFragment : Fragment() {
    private var  _binding : FragmentAttemptsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttemptsBinding.inflate(inflater, container, false)

        viewModel.attempts.observe(viewLifecycleOwner)  {
            binding.attemptNumText.text = it.toString()
        }



        return binding.root
    }

}