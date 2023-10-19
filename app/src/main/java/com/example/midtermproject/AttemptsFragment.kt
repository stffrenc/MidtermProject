package com.example.midtermproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
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
        val LOG = "AttemptsFragment"
        _binding = FragmentAttemptsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.updateAttemptString()
        binding.attemptNumText.text = viewModel.attemptString.value.toString()
        viewModel.attemptString.observe(viewLifecycleOwner){
            Log.d(LOG, "observation works")
            binding.attemptNumText.text = viewModel.attemptString.value
        }
        return binding.root
    }

}