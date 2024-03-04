package com.example.approom

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.approom.databinding.ActivityNotesMainBinding
import com.example.approom.notelist.IAdapter
import com.example.approom.notelist.NoteAdapter
import com.example.approom.notelist.Notes
import com.example.approom.notelist.NotesViewModel

class NotesMainActivity : AppCompatActivity() , IAdapter {
    lateinit var binding: ActivityNotesMainBinding

    private lateinit var viewModel: NotesViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes_main)

        val recyclerView: RecyclerView =findViewById(R.id.rvNoteList)
        recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter = NoteAdapter(this)
        recyclerView.adapter=adapter
        //initialize viewmodel
        viewModel= ViewModelProvider(this)[NotesViewModel::class.java]
        //set viewmodel to update our recyclerview
        viewModel.allNotes.observe(this, Observer{
            adapter.updateData(it)
        })
        binding.btnAddNote.setOnClickListener{
            val intent= Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }


    }


    override fun onNoteClick(notes: Notes){
        viewModel.deleteNote(notes)
    }
}