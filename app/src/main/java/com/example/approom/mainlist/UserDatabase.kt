package com.example.approom.mainlist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){

    abstract fun getUserData():UserDao

    companion object {
        @Volatile
        var Instant: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase {
            val tempInstant = Instant
            if (tempInstant != null) return tempInstant
            synchronized(this) {
                val instant = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "staff_table"
                ).build()
                Instant = instant
                return instant
            }
        }
    }
}