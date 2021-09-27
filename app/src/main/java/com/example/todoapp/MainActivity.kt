package com.example.todoapp

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.databinding.PopupActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var popupBinding : PopupActivityBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        //Display main activity view
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Create an instance of the database
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database"
        ).allowMainThreadQueries().build()
        //Get instance of the Dao
        val todoDao = db.todoDao()

        //Uncomment to clear database
        //todoDao.deleteAll()

        //Get all items in database and display
        val items = todoDao.getAll().toMutableList()
        val todoAdapter = TodoAdapter(items)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = todoAdapter
        //Update todoStates
        todoAdapter.onItemClick = { todo ->
            todo.isDone = todo.isDone != true
            todoDao.updateOne(todo)
        }

        //Open Popup dialog while clicking on addActionButton
        val popupDialog = Dialog(this)
        binding.addActionButton.setOnClickListener {
            popupBinding = PopupActivityBinding.inflate(layoutInflater)
            val popupView = popupBinding.root
            popupDialog.setContentView(popupView)
            popupDialog.show()

            //Dismiss popup while clicking on buttonCancel
            popupBinding.buttonCancel.setOnClickListener{
                popupDialog.dismiss()
            }
            //Add new todoComponent, refresh content
            popupBinding.buttonCreate.setOnClickListener{
                val todo = Todo(text = popupBinding.editTextTodoText.text.toString(), isDone = false)
                todoDao.insertOne(todo)
                items.clear()
                items.addAll(todoDao.getAll())
                todoAdapter.notifyDataSetChanged()
                popupDialog.dismiss()
            }
        }
    }
}