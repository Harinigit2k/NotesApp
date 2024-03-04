package com.example.approom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.approom.databinding.ActivityRegisterBinding
import com.example.approom.mainlist.User
import com.example.approom.mainlist.UserViewModel
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.bSave.setOnClickListener {
            loadData()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loadData() {

        binding.apply {
            val name = etUserName.text.toString()
            val password = etPassword.text.toString()
            val rePassword = etRePassword.text.toString()
            if (name.isBlank() || password.isBlank() || rePassword.isBlank()) {
                Toast.makeText(this@RegisterActivity, "Enter all Field!!",
                    Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    if (password == rePassword) {
                        val user= User(name = name, password = password)
                        Toast.makeText(this@RegisterActivity, "Successfully Added!!",
                            Toast.LENGTH_SHORT).show()
                        viewModel.addUser(user)
                    } else
                        Toast.makeText(this@RegisterActivity, "Password is not same!!",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}