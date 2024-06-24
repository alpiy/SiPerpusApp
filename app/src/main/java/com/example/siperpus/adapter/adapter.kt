package com.example.siperpus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.siperpus.buku.DataItem
import com.bumptech.glide.Glide
import com.example.siperpus.databinding.ListBookBinding

class StoriesAdapter : ListAdapter<DataItem, StoriesAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    class MyViewHolder(
        private val binding: ListBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            with(binding){
                tittle.text = user.bookTitle
                author.text = user.author
                date.text = user.releaseDate
                Glide.with(root.rootView)
                    .load(user.bookPict)
                    .into(coverBook)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}