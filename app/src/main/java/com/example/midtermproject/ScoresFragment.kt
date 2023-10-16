package com.example.midtermproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.midtermproject.databinding.FragmentScoresBinding

class ScoresFragment : Fragment() {
    val TAG = "ScoresFragment"
    private var _binding: FragmentScoresBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoresBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = ScoreDatabase.getInstance(application).scoreDao
        val viewModelFactory = ScoresViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ScoresViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        fun scoreClicked (scoreId : Long) {
            viewModel.onScoreClicked(scoreId)
        }
        fun yesPressed(scoreId : Long) {
            Log.d(TAG, "in yesPressed(): noteId = $scoreId")
            binding.viewModel?.deleteScore(scoreId)
        }
        fun deleteClicked (scoreId : Long) {
            ConfirmDeleteDialogFragment(scoreId,::yesPressed).show(childFragmentManager,
                ConfirmDeleteDialogFragment.TAG)
        }


        val adapter = ScoreItemAdapter(::scoreClicked,::deleteClicked)

        val recyclerView = binding.scoresList
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.scoresList.adapter = adapter

        viewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return view
    }


}