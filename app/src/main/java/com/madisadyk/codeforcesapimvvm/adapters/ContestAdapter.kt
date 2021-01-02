package com.madisadyk.codeforcesapimvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.madisadyk.codeforcesapimvvm.R
import com.madisadyk.codeforcesapimvvm.models.Contest
import kotlinx.android.synthetic.main.item_contest_preview.view.*

class ContestAdapter : RecyclerView.Adapter<ContestAdapter.ContestViewHolder>() {
    inner class ContestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Contest>() {
        override fun areItemsTheSame(oldItem: Contest, newItem: Contest): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Contest, newItem: Contest): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestAdapter.ContestViewHolder {
        return ContestViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_contest_preview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Contest) -> Unit)? = null

    override fun onBindViewHolder(holder: ContestAdapter.ContestViewHolder, position: Int) {
        val contest = differ.currentList[position]
        holder.itemView.apply {
            contestNameTV.text = contest.name
            beforeStartTV.text = contest.startTimeSeconds.toString()
            lengthOfContestTV.text = contest.durationSeconds.toString()
        }
    }

    fun setOnItemClickListener(listener: (Contest) -> Unit) {
        onItemClickListener = listener
    }

}