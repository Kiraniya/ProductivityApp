package com.example.productivity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todoentity(
    @PrimaryKey(autoGenerate = true)
    var roomId:Long?=null,
    val text:String
)
