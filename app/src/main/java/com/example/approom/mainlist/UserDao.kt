package com.example.approom.mainlist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao{
    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readUserData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE name = (:userName) AND password = (:password)")
    suspend fun checkUser(userName: String, password: String): User?

    @Delete
    suspend fun deleteUser(user: User)
}


