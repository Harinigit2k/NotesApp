package com.example.approom.mainlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).getUserData()
        repository = UserRepository(userDao)
        readAllData = repository.readData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    suspend fun checkUser(userName: String, userPassword: String): User? {
        val result = viewModelScope.async {
            repository.checkUser(userName, userPassword)
        }
        return result.await()
    }

    fun delete(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }

    }
}