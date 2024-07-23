package com.example.question2

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var editText: EditText
    private lateinit var addButton: Button
    private lateinit var prioritySpinner: Spinner
    private lateinit var adapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listView)
        editText = findViewById(R.id.editTextTask)
        addButton = findViewById(R.id.addButton)
        prioritySpinner = findViewById(R.id.prioritySpinner)
        adapter = TaskAdapter(this, tasks)
        listView.adapter = adapter

        addButton.setOnClickListener {
            val task = editText.text.toString()
            val priority = prioritySpinner.selectedItem.toString()
            if (task.isNotEmpty()) {
                val newTask = Task(task, priority)
                tasks.add(newTask)
                adapter.notifyDataSetChanged()
                editText.text.clear()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        val priorities = arrayOf("Low", "Medium", "High")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priorities)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        prioritySpinner.adapter = spinnerAdapter

        prioritySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            tasks[position].isDone = !tasks[position].isDone
            adapter.notifyDataSetChanged()
        }

        listView.setOnItemLongClickListener { parent, view, position, id ->
            tasks.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }
    }

    data class Task(val description: String, val priority: String, var isDone: Boolean = false)

    class TaskAdapter(context: MainActivity, private val tasks: List<Task>) : ArrayAdapter<Task>(context, 0, tasks) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val task = getItem(position)!!
            val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
            val textView = view.findViewById<TextView>(android.R.id.text1)
            textView.text = task.description

            val color = when (task.priority) {
                "Low" -> Color.rgb(112, 111, 211)
                "Medium" -> Color.rgb(255, 121, 63)
                "High" -> Color.RED
                else -> Color.BLACK
            }
            textView.setTextColor(color)

            if (task.isDone) {
                textView.paintFlags = textView.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                textView.paintFlags = textView.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }

            return view
        }
    }
}