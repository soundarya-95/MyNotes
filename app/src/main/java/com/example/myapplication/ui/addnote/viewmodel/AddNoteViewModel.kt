package com.example.myapplication.ui.addnote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Note
import com.example.myapplication.data.repository.NoteRepository
import kotlinx.coroutines.launch

class AddNoteViewModel(private val repository: NoteRepository) : ViewModel() {

    fun addNote(message: String) {
        val newNote = Note(
            id = 0,
            message = message,
            isPending = true
        )
        viewModelScope.launch {
            repository.insertNote(newNote)
        }
    }
}