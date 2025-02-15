package com.example.todolist.ui.navigation

import TaskScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.theme.TaskDetailScreen


@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            TaskScreen(navController)
        }
        composable(route= "task_detail/{taskId}"){
            backStackEntry ->
                val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            taskId.let { TaskDetailScreen(navController, taskId) }
    }
    }
}
