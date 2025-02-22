package com.example.todolist.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.data.TaskRepository

class ViewModelFactory(private val repository: TaskRepository):ViewModelProvider.Factory {
    override fun <T: ViewModel> create (modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(TaskViewModel ::class.java)){
            return  TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("uknown view model class")
    }

}