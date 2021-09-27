package com.example.todoapp
import androidx.room.Entity
import androidx.room.PrimaryKey

//Class for item stored in database
@Entity(tableName = "todoList")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    var text: String,
    var isDone: Boolean
) {
    constructor(text: String, isDone: Boolean) : this(null, text, isDone)
}