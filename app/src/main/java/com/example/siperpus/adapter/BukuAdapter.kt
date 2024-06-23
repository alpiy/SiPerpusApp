package com.example.siperpus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.siperpus.R
import com.example.siperpus.buku.Buku
import dagger.hilt.android.AndroidEntryPoint


class BukuAdapter(private val bukulist: List<Buku>) : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>() {

    class BukuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewJudul: TextView = itemView.findViewById(R.id.tittle)
        val textViewAuthor: TextView = itemView.findViewById(R.id.author)
        val textViewTahun: TextView = itemView.findViewById(R.id.date)
        val imageViewCover: ImageView = itemView.findViewById(R.id.cover_book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_book, parent, false)
        return BukuViewHolder(itemView)
    }

    override fun getItemCount() = bukulist.size

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val currentItem = bukulist[position]
        holder.textViewJudul.text = currentItem.judul
        holder.textViewAuthor.text = currentItem.author
        holder.textViewTahun.text = currentItem.tahunRilis
        holder.imageViewCover.setImageURI(currentItem.imageUri)

    }
}