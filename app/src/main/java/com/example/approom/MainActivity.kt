package com.example.approom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.approom.databinding.ActivityMainBinding
import com.example.approom.databinding.ListItemBinding
import com.example.approom.mainlist.UserViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.tvNewUser.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.apply {
            binding.bLogin.setOnClickListener {
                val name = binding.etUser.text.toString()
                val password = binding.etPassword.text.toString()
                if (name.isNotBlank() && password.isNotBlank()) {
                    lifecycleScope.launch{
                        val user = viewModel.checkUser(name, password)
                        if (name == "admin" && password == "admin") {
                            val intent = Intent(this@MainActivity, ListItemBinding::class.java)
                            startActivity(intent)
                        } else {
                            if (user == null) {
                                Toast.makeText(this@MainActivity,
                                    "In-Correct User or Password",
                                    Toast.LENGTH_SHORT).show()
                            } else {
                                val intent = Intent(this@MainActivity,NotesMainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}
