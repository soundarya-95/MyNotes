package com.example.myapplication.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Note
import com.example.myapplication.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NoteRepository) : ViewModel() {
    private val _pendingNotes = MutableStateFlow<List<Note>>(emptyList())
    val pendingNotes: StateFlow<List<Note>> = _pendingNotes.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allNotes.collect { notes ->
                _pendingNotes.value = notes.filter { it.isPending }
            }
        }
    }
}
