package com.example.siperpus.member
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.siperpus.R
import com.example.siperpus.adapter.adapterMember
import com.example.siperpus.buku.UpdateAddBukuActivity
import com.example.siperpus.databinding.ActivityListMemberBinding


class ListMemberActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityListMemberBinding
    private val viewModel by viewModels<MemberViewModel> {
        MemberViewModelFactory.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        viewModel.getListMember()
        viewModel.dataMember.observe(this) { listMember ->
            if (listMember != null) {
                setStoriesData(listMember)
            }
        }
        binding.btnAddM.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.listMember.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.listMember.addItemDecoration(itemDecoration)
    }

    private fun setStoriesData(userData: List<DataMember>?) {
        val adapter = adapterMember()
        adapter.submitList(userData)
        binding.listMember.adapter = adapter
    }

    override fun onClick(v: View?) {
        when (v?.getId()) {
            R.id.btnAdd_m -> {
                val intentUpdate =
                    Intent(this@ListMemberActivity, UpdateAddBukuActivity::class.java)
                startActivity(intentUpdate)
            }
        }
    }
}