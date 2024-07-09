package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.addnote.AddNoteScreen
import com.example.myapplication.ui.addnote.viewmodel.AddNoteViewModel
import com.example.myapplication.ui.main.MainScreen
import com.example.myapplication.ui.main.viewmodel.MainViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "main",
    mainViewModel: MainViewModel,
    addNoteViewModel: AddNoteViewModel
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("main") {
            MainScreen(navController, mainViewModel)
        }
        composable("addNote") {
            AddNoteScreen(navController, addNoteViewModel)
        }
    }
}
