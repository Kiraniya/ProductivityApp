package com.example.productivity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todoentity::class], version = 1)
abstract class Tododatabase:RoomDatabase() {
    abstract fun todoDao():TodoDao

    //creating a singleton instance is only created once
    companion object{
        private var INSTANCE:Tododatabase?=null
        fun getInstance(context: Context):Tododatabase{
            if(INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context,
                    Tododatabase::class.java,"dbmain")
                    .fallbackToDestructiveMigration().build()
            }
            return INSTANCE as Tododatabase
        }
    }
}