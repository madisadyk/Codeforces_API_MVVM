package com.madisadyk.codeforcesapimvvm.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.madisadyk.codeforcesapimvvm.R
import com.madisadyk.codeforcesapimvvm.models.CFHandler
import kotlinx.android.synthetic.main.item_handler_preview.view.*


class HandleAdapter : RecyclerView.Adapter<HandleAdapter.HandleViewHolder>() {
    inner class HandleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<CFHandler>() {
        override fun areItemsTheSame(oldItem: CFHandler, newItem: CFHandler): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CFHandler, newItem: CFHandler): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HandleViewHolder {
        return HandleViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_handler_preview,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((CFHandler) -> Unit)? = null

    override fun onBindViewHolder(holder: HandleViewHolder, position: Int) {
        val handle = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).asBitmap().load(handle.titlePhoto).centerCrop().into(handlerImgIV)
            handlerRankTV.text = handle.rank
            handlerTV.text = handle.handle
            ratingTV.text = handle.rating.toString() + " (max. " + handle.maxRank + ", " + handle.maxRating +")"

            setOnClickListener {
                onItemClickListener?.let { it(handle) }
            }
        }
    }

    fun setOnItemClickListener(listener: (CFHandler) -> Unit) {
        onItemClickListener = listener
    }
}