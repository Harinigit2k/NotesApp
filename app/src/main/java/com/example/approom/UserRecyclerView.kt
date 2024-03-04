package com.example.approom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.approom.databinding.ListItemBinding
import com.example.approom.mainlist.User

class UserRecyclerView(): RecyclerView.Adapter<UserRecyclerView.NewHolder>() {

    var userList = emptyList<User>()
    private var onDeleteClick: ((User) -> Unit)? = null

    fun setOnDeleteClickListener(listener: (User) -> Unit) {
        this.onDeleteClick = listener
    }

    class NewHolder(var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {

            binding.apply {
                tvUserName.text = user.name
                tvPassword.text = user.password
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewHolder(view)
    }

    override fun onBindViewHolder(holder: NewHolder, position: Int) {
        val list = userList[position]
        holder.bind(list)
        holder.binding.ibDelete.setOnClickListener { onDeleteClick?.invoke(list) }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user:List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}

