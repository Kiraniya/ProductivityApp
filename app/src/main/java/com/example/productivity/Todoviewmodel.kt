package com.example.productivity

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Todoviewmodel
@Inject constructor(
    private val Todorepo:Todorepo,
):ViewModel(),TodoviewmodelAbs {
    private val ioScope= CoroutineScope(Dispatchers.IO)
    override val noteListFlow: Flow<List<Todoentity>>
         = Todorepo.getAllFlow()

    override fun addNote(note: Todoentity){
        ioScope.launch { Todorepo.insert(note= note) }
    }

    override fun updateNote(note: Todoentity){
        ioScope.launch { Todorepo.update(note= note) }
    }

    override fun deleteNote(note: Todoentity){
        ioScope.launch { Todorepo.delete(note= note) }
    }
}


interface TodoviewmodelAbs {
    val noteListFlow: Flow<List<Todoentity>>
    fun addNote(note: Todoentity)
    fun updateNote(note: Todoentity)
    fun deleteNote(note: Todoentity)
}

