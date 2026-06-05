package com.example.videofeed.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.videofeed.R
import com.example.videofeed.ui.feed.FeedActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUrlInput = findViewById<EditText>(R.id.etUrlInput)
        val btnStartFeed = findViewById<Button>(R.id.btnStartFeed)

        btnStartFeed.setOnClickListener {
            val url = etUrlInput.text.toString().trim()
            if (url.isNotEmpty() && (url.startsWith("http://") || url.startsWith("https://"))) {
                val intent = Intent(this, FeedActivity::class.java)
                intent.putExtra("EXTRA_TARGET_URL", url)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Masukkan URL yang valid (https://...)", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
