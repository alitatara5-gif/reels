package com.example.videofeed.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.videofeed.R
import com.example.videofeed.ui.feed.FeedActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnStartFeed).setOnClickListener {
            startActivity(Intent(this, FeedActivity::class.java))
        }
    }
}
