package com.example.myapplication.data.repository


import androidx.annotation.WorkerThread
import com.example.myapplication.data.db.dao.NoteDao
import com.example.myapplication.data.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }
}
