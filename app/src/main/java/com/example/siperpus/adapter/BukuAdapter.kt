package com.example.siperpus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.siperpus.R
import com.example.siperpus.buku.Buku


class BukuAdapter( private val context:Context, private val datalist: ArrayList<Buku>) : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>() {
    class BukuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewJudul: TextView = itemView.findViewById(R.id.tittle)
        val textViewAuthor: TextView = itemView.findViewById(R.id.author)
        val textViewTahun: TextView = itemView.findViewById(R.id.date)
        val imageViewCover: ImageView = itemView.findViewById(R.id.cover_book)
        val cvBook: CardView = itemView.findViewById(R.id.cv_book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_book, parent, false)
        return BukuViewHolder(itemView)
    }

    override fun getItemCount(): Int = datalist.size

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        holder.textViewJudul.text = datalist.get(position).book_title
        holder.textViewAuthor.text = datalist.get(position).author
        holder.textViewTahun.text = datalist.get(position).release_date
//        holder.imageViewCover.setImageURI(datalist.get(position).imageUri)
        holder.cvBook.setOnClickListener{
            Toast.makeText(context,""+datalist.get(position).book_title,Toast.LENGTH_SHORT).show()
        }

    }

    fun setData(data: List<Buku>){
        datalist.clear()
        datalist.addAll(data)
        notifyDataSetChanged()
    }
}