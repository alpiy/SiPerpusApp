package com.example.siperpus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.siperpus.buku.DataItem
import com.example.siperpus.databinding.ListBookBinding
import com.example.siperpus.databinding.ListMemberBinding
import com.example.siperpus.member.DataMember

class adapterMember : ListAdapter<DataMember, adapterMember.MemberViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = ListMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = getItem(position)
        holder.bind(member)
    }

    class MemberViewHolder(
        private val binding: ListMemberBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(member: DataMember) {
            with(binding){
                fullname.text = member.fullname
                gender.text = member.gender
                address.text = member.address
                Glide.with(root.rootView)
                    .load(member.memberPict)
                    .into(picturePict)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataMember>() {
            override fun areItemsTheSame(oldItem: DataMember, newItem: DataMember): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DataMember, newItem: DataMember): Boolean {
                return oldItem == newItem
            }
        }
    }
}