package dev48n02m41.socialmediamoodtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev48n02m41.socialmediamoodtracker.ui.AskActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openToDoListActivity(view: View) {
        val x = Intent(this, AskActivity::class.java)

        startActivity(x)
    }
}