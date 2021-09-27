package com.example.todoapp

import androidx.room.*

//Class used to perform queries on dataBase
@Dao
interface TodoDao {
    @Query("SELECT * FROM todoList ORDER BY isDone ASC")
    fun getAll(): List<Todo>

    @Query("SELECT COUNT(*) FROM todoList")
    fun getSize(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(todo: Todo)

    @Update
    fun updateOne(todo: Todo)

    @Query("DELETE FROM todoList")
    fun deleteAll()

}