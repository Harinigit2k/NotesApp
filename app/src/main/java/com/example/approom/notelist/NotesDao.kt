package com.example.approom.notelist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
abstract class NotesDao {

    // onConflict is used ignore the duplicate data
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract  suspend fun insert(notes: Notes)

    @Update
    abstract suspend fun update(notes: Notes)

    @Delete
    abstract suspend fun delete(notes: Notes)


    // getting all the notes and showing them to the recyclerView in the MainActivity
    @Query("SELECT * FROM note_table ORDER BY id DESC")
    abstract fun getAllNotes() : LiveData<List<Notes>>



}