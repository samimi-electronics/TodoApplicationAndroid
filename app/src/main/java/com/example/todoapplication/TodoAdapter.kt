package com.example.todoapplication

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.databinding.ItemTodoBinding

class TodoAdapter(
    private val todos: MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false);
        return TodoViewHolder(binding);
//        return TodoViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.item_todo,
//                parent,
//                false,
//            )
//        );
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        with(holder) {
            with(todos[position]) {
                binding.tvTodoTitle.text = title
                binding.cbDone.isChecked = checked
                toggleStrikeThrough(binding.tvTodoTitle, checked)
                binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                    toggleStrikeThrough(binding.tvTodoTitle, isChecked)
                    checked = !checked
                }
            }
        }

        /*
        val currentTodo = todos[position];
        holder.itemView.apply {
            val tvTodoTitle = findViewById<TextView>(R.id.tvTodoTitle);
            val cbDone = findViewById<CheckBox>(R.id.cbDone);

            tvTodoTitle.text = currentTodo.title;
            cbDone.isChecked = currentTodo.checked;

            toggleStrikeThrough(tvTodoTitle, currentTodo.checked);

            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked);
                currentTodo.checked = !currentTodo.checked;
            }
        }*/
    }

    override fun getItemCount(): Int {
        return todos.size;
    }

    fun addTodo(todo: Todo) {
        todos.add(todo);
        notifyItemInserted(todos.size - 1);
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.checked
        }
        notifyDataSetChanged();
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}