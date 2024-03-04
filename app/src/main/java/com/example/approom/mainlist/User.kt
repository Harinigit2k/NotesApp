package com.example.approom.mainlist

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    var name: String,
    var password: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


