package com.example.approom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.approom.databinding.ActivityAddNoteBinding
import com.example.approom.notelist.Notes
import com.example.approom.notelist.NotesViewModel

class AddNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddNoteBinding

    private lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)

        //initialize view model
        viewModel= ViewModelProvider(this).get(NotesViewModel::class.java)
        binding.btnSave.setOnClickListener{
            addingNote()
        }
    }

    private fun addingNote() {
        val title: EditText = findViewById(R.id.ed_title)
        val desc: EditText = findViewById(R.id.ed_desc)
        val Title = title.text.toString()
        val Desc = desc.text.toString()
        if (Title.isNotEmpty() && Desc.isNotEmpty()) {
            //need viewmodel here
            viewModel.insertNote(Notes(0, Title, Desc))
            val intent = Intent(this, NotesMainActivity::class.java)
            startActivity(intent)

        }

    }

}