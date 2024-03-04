package com.example.approom.notelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.approom.R

class NoteAdapter(private val listener: IAdapter) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var allNotes = ArrayList<Notes>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val desc: TextView = itemView.findViewById(R.id.desc)
        val delete: ImageButton = itemView.findViewById(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
        viewHolder.delete.setOnClickListener{
            listener.onNoteClick(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = allNotes[position]
        holder.title.text = currentItem.title
        holder.desc.text = currentItem.desc
    }

    fun updateData(newData:List<Notes>){
        allNotes.clear()
        allNotes.addAll(newData)
        notifyDataSetChanged()
    }

}

interface IAdapter{

    fun onNoteClick(notes: Notes)
}
