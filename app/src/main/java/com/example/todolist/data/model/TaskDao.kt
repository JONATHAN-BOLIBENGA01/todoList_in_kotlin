package com.example.todolist.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY dateFin ASC")
    fun getAllTask(): Flow<List<Task>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun nsertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun  deleteTask(task: Task)
}