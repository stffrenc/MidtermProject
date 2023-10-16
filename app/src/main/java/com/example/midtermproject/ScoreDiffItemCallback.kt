package com.example.midtermproject

import androidx.recyclerview.widget.DiffUtil
class ScoreDiffItemCallback : DiffUtil.ItemCallback<Score>(){
    override fun areItemsTheSame(oldItem: Score, newItem: Score)
            = (oldItem.scoreId == newItem.scoreId)
    override fun areContentsTheSame(oldItem: Score, newItem: Score) = (oldItem == newItem)
}