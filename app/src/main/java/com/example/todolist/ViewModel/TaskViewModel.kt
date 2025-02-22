package com.example.todolist.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolist.data.Task
import com.example.todolist.data.TaskRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class TaskViewModel (private val repository: TaskRepository): ViewModel() {

    val tasks= repository.taks

    fun addTask(name: String, dateDebut: LocalDate, dateFin: LocalDate) {
      viewModelScope.launch {
            repository.addTask(name, dateDebut, dateFin)
        }

    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}

