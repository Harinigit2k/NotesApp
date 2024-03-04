package com.example.approom.notelist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1, exportSchema = false)

abstract  class NotesDatabase: RoomDatabase() {
    abstract fun getNotesDao(): NotesDao

    companion object {
        @Volatile
        var INSTANCE: NotesDatabase? = null
        fun getInstance(context: Context): NotesDatabase? {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "NotesDatabase"
                ).build()
                INSTANCE =instance
                instance
            }

        }

    }

}
