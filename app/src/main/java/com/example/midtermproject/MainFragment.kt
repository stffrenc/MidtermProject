package com.example.midtermproject

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.midtermproject.databinding.FragmentMainBinding
import com.example.midtermproject.databinding.FragmentScoresBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

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
        binding.viewModel = viewModel

        binding.playButton.setOnClickListener {  }

        binding.highScoreButton.setOnClickListener {  }
        return binding.root
    }



}