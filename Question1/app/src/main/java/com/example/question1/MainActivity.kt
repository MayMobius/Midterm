package com.example.question1

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val edtxt1: EditText = findViewById(R.id.ednum1)
        val edtxt2: EditText = findViewById(R.id.ednum2)
        val resultTV: TextView = findViewById(R.id.textResult)
        val btSum: Button = findViewById(R.id.btSum)
        val btSub: Button = findViewById(R.id.btSub)
        val btMulti: Button = findViewById(R.id.btMulti)
        val btDiv: Button = findViewById(R.id.btDiv)

        btSum.setOnClickListener { view ->
            val x = edtxt1.text.toString().toIntOrNull() ?: 0
            val y = edtxt2.text.toString().toIntOrNull() ?: 0
            resultTV.text = sum(x, y).toString()
        }

        btSub.setOnClickListener { view ->
            val x = edtxt1.text.toString().toIntOrNull() ?: 0
            val y = edtxt2.text.toString().toIntOrNull() ?: 0
            resultTV.text = subtract(x, y).toString()
        }

        btMulti.setOnClickListener { view ->
            val x = edtxt1.text.toString().toIntOrNull() ?: 0
            val y = edtxt2.text.toString().toIntOrNull() ?: 0
            resultTV.text = multiply(x, y).toString()
        }

        btDiv.setOnClickListener { view ->
            val x = edtxt1.text.toString().toIntOrNull() ?: 0
            val y = edtxt2.text.toString().toIntOrNull() ?: 1
            resultTV.text = divide(x, y).toString()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayput)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun sum(a: Int, b: Int): Int {
        return a + b
    }

    private fun subtract(a: Int, b: Int): Int {
        return a - b
    }

    private fun multiply(a: Int, b: Int): Int {
        return a * b
    }

    private fun divide(a: Int, b: Int): Int {
        return if (b != 0) a / b else 0
    }
}