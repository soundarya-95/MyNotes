package com.example.myapplication.ui.addnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.addnote.viewmodel.AddNoteViewModel

@Composable
fun AddNoteScreen(navController: NavController, viewModel: AddNoteViewModel) {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Add New Task", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Task") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.addNote(message)
                navController.navigateUp()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save Task")
        }
    }
}
