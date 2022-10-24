package com.example.todoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.databinding.ActivityMainBinding
import com.example.todoapplication.databinding.ItemTodoBinding

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var activityMainBinding: ActivityMainBinding;
    private lateinit var itemTodoBinding: ItemTodoBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        itemTodoBinding = ItemTodoBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        todoAdapter = TodoAdapter(mutableListOf())


        activityMainBinding.rvTodoItems.adapter = todoAdapter;
        activityMainBinding.rvTodoItems.layoutManager = LinearLayoutManager(this)


        activityMainBinding.btnAddTodo.setOnClickListener {
            val todoTitle = activityMainBinding.etTodoTitle.text.toString();
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                itemTodoBinding.tvTodoTitle.text = ""
            }
        }
        activityMainBinding.btnRemoveChecked.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}