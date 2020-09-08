package dev48n02m41.socialmediamoodtracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev48n02m41.socialmediamoodtracker.R
import dev48n02m41.socialmediamoodtracker.ui.adapters.MoodLogListAdapter
import dev48n02m41.socialmediamoodtracker.ui.viewmodels.DiaryEntryViewModel

private lateinit var diaryEntryViewModel: DiaryEntryViewModel

class MoodLogListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_log_list)

        // Recycler setup.
        recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = MoodLogListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true);

        // ViewModel
        diaryEntryViewModel = ViewModelProvider(this).get(DiaryEntryViewModel::class.java)

        diaryEntryViewModel.allDiaryEntries.observe(this, { results ->
            results?.let {
                (adapter as MoodLogListAdapter).setMoodLogsList(results)
                Log.d(MoodLogListActivity.TAG, "Number of diary entries in database right now: " + results.size)
            }
        })

    }

    companion object {
        private const val TAG = "MoodLogListActivity"
    }
}