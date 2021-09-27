package com.example.todoapp
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.RecyclerViewTodoBinding

//Adapter Class for TodoItems
class TodoAdapter(
    private val items: List<Todo>,
    var onItemClick: ((Todo) -> Unit)? = null
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewTodoBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private var binding: RecyclerViewTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.todoText.text = todo.text
            binding.checkBox.isChecked = todo.isDone
            binding.checkBox.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }
}