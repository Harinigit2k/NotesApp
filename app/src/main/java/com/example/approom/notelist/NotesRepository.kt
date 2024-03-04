package com.example.approom.notelist




class NotesRepository(private val noteDao: NotesDao) {
    val allNotes=noteDao.getAllNotes()
    suspend fun insert(notes: Notes){
        noteDao.insert(notes)
    }
    suspend fun delete(notes: Notes){
        noteDao.delete(notes)
    }

}