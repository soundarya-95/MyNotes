// AddNoteViewModelFactory.kt
package com.example.myapplication.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.db.dao.NoteDao
import com.example.myapplication.data.db.database.NoteDatabase
import com.example.myapplication.ui.addnote.viewmodel.AddNoteViewModel
import com.example.myapplication.ui.main.viewmodel.MainViewModel
import com.example.myapplication.data.repository.NoteRepository

class NoteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java)) {
            val repository = NoteRepository(getNotesDatabase(context))
            return AddNoteViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val repository = NoteRepository(getNotesDatabase(context))
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    private fun getNotesDatabase(context: Context): NoteDao {
        return NoteDatabase.getDatabase(context)
            .noteDao()
    }
}
