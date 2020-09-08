package dev48n02m41.socialmediamoodtracker.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev48n02m41.socialmediamoodtracker.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openAskActivity(view: View) {
        val x = Intent(this, AskActivity::class.java)
        startActivity(x)
    }

    fun openMoodLogListActivity(view: View) {
        val x = Intent(this, MoodLogListActivity::class.java)
        startActivity(x)
    }

}