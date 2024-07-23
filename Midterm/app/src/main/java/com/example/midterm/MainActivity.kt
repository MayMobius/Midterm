package com.example.midterm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openCalculatorButton: Button = findViewById(R.id.openCalculatorButton)
        val openTaskManagerButton: Button = findViewById(R.id.openTaskManagerButton)

        openCalculatorButton.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            intent.putExtra("message", "Hello from MainActivity!")
            startActivity(intent)
        }

        openTaskManagerButton.setOnClickListener {
            val intent = Intent(this, TaskManagerActivity::class.java)
            intent.putExtra("message", "Hello from MainActivity!")
            startActivity(intent)
        }
    }
}