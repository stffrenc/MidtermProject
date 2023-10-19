package com.example.midtermproject
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.midtermproject.Score
import com.example.midtermproject.ScoreDiffItemCallback
import com.example.midtermproject.databinding.ScoreItemBinding
import kotlin.reflect.KFunction1
class ScoreItemAdapter(
    private val deleteClickListener: (noteId: Long) -> Unit)
    : ListAdapter<Score, ScoreItemAdapter.ScoreItemViewHolder>(ScoreDiffItemCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ScoreItemViewHolder = ScoreItemViewHolder.inflateFrom(parent)
    override fun onBindViewHolder(holder: ScoreItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, deleteClickListener)
    }
    class ScoreItemViewHolder(private val binding: ScoreItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): ScoreItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ScoreItemBinding.inflate(layoutInflater, parent, false)
                return ScoreItemViewHolder(binding)
            }
        }

        fun bind(item: Score, deleteClickListener: (scoreId: Long) -> Unit) {
            binding.score = item
            binding.deleteButton.setOnClickListener { deleteClickListener(item.scoreId) }

        }
    }
    }