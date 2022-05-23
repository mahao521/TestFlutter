package com.example.testflutter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var tvContent = findViewById<TextView>(R.id.tv_content)
        var flutterContent = intent.getStringExtra("name")
        tvContent.setText(flutterContent)
        tvContent.setOnClickListener {
            var intent = Intent()
            intent.putExtra("message", "原生界面传递给flutter")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}