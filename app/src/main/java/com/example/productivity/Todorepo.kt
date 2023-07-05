package com.example.productivity

import kotlinx.coroutines.flow.Flow


class Todorepo(private val todoDao:TodoDao){
    fun getAllFlow():Flow<List<Todoentity>> = todoDao.getAllFlow()
    suspend fun insert(note: Todoentity)= todoDao.insert(note=note)
    suspend fun update(note: Todoentity)= todoDao.update(note=note)
    suspend fun delete(note: Todoentity)= todoDao.delete(note=note)
}
