package com.example.productivity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {
    @Query("select * from Todoentity")
    fun getAllFlow(): Flow<List<Todoentity>>

    @Insert
    suspend fun insert(note:Todoentity)

    @Update
    suspend fun update(note: Todoentity)

    @Delete
    suspend fun delete(note:Todoentity)
}