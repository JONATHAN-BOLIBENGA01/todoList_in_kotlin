package com.example.todolist.data

import com.example.todolist.data.model.TaskDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

import java.time.LocalDate

class TaskRepository(private val taskDAO : TaskDao) {
   val taks: Flow<List<Task>> = taskDAO.getAllTask()
    suspend fun addTask(name : String, dateDebut : LocalDate, dateFin : LocalDate){
        val task = Task(name = name, dateDebut = dateDebut, dateFin = dateFin)
        taskDAO.nsertTask(task)
    }

   suspend fun  updateTask(task: Task){
        taskDAO.updateTask(task)
    }

     suspend fun deleteTask(task: Task){
        taskDAO.deleteTask(task)
    }

}