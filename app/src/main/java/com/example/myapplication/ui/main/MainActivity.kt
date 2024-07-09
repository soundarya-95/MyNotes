package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.data.model.Note
import com.example.myapplication.navigation.NavGraph
import com.example.myapplication.ui.NoteViewModelFactory
import com.example.myapplication.ui.NotesAppTheme
import com.example.myapplication.ui.Typography
import com.example.myapplication.ui.addnote.viewmodel.AddNoteViewModel
import com.example.myapplication.ui.main.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels { NoteViewModelFactory(this) }
    private val addNoteViewModel: AddNoteViewModel by viewModels { NoteViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                NavGraph(
                    mainViewModel = mainViewModel,
                    addNoteViewModel = addNoteViewModel
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addNote") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = "Pending Tasks", style = MaterialTheme.typography.h4)
                NotesList(viewModel = viewModel)
            }
        }
    )
}

@Composable
fun NotesList(viewModel: MainViewModel) {
    val notes by viewModel.pendingNotes.collectAsState(initial = emptyList())
    if (notes.isEmpty()) {
        Text(text = "No pending tasks", style = MaterialTheme.typography.body1)
    } else {
        LazyColumn(contentPadding = PaddingValues(vertical = 16.dp)) {
            items(items = notes, itemContent = {
                NotesListItem(entry = it)
            })
        }
    }
}

@Composable
fun NotesListItem(entry: Note) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = entry.message, style = Typography.body1)
                Text(text = "id: ${entry.id}", style = Typography.body2)
            }
        }
    }
}
