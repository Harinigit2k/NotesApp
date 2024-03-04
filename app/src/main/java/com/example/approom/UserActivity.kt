package com.example.approom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.approom.databinding.ActivityUserBinding
import com.example.approom.mainlist.UserViewModel

class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding
    lateinit var adapter: UserRecyclerView
    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.rrOne.layoutManager = LinearLayoutManager(this)
        adapter = UserRecyclerView()
        binding.rrOne.adapter = adapter
        viewModel.readAllData.observe(this, Observer { user->
            adapter.setData(user)
        })
        adapter.setOnDeleteClickListener {
            viewModel.delete(it)
        }
    }
}