package dev48n02m41.socialmediamoodtracker.ui

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import dev48n02m41.socialmediamoodtracker.R
import kotlinx.android.synthetic.main.activity_main.*

private lateinit var textViewHeader: TextView
private lateinit var btnAsk: Button
private lateinit var btnList: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        handleUI()
        animate()
    }

    private fun handleUI() {
        textViewHeader = findViewById(R.id.header1)
        btnAsk = findViewById(R.id.btn_ask)
        btnList = findViewById(R.id.btn_list)
    }

    private fun animate() {
        ObjectAnimator.ofFloat(textViewHeader, "scaleX", 1.015f).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(textViewHeader, "scaleY", 1.01f).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }

        ObjectAnimator.ofFloat(btnAsk, "scaleX", 1.01f).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(btnAsk, "scaleY", 1.01f).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(btnList, "scaleX", 1.01f).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(btnList, "scaleY", 1.01f).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
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