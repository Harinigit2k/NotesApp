package com.example.approom.mainlist

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val readData: LiveData<List<User>> = userDao.readUserData()

    suspend fun addUser(user:User) {
        userDao.addUser(user)
    }

    suspend fun checkUser(user: String, password: String):User? {
        return userDao.checkUser(user, password)
    }

    suspend fun deleteUser(user:User) {
        userDao.deleteUser(user)
    }
}