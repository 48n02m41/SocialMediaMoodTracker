package dev48n02m41.socialmediamoodtracker.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev48n02m41.socialmediamoodtracker.R
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity

class MoodLogListAdapter internal constructor(context: Context) : RecyclerView.Adapter<MoodLogListAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var moodLogsList = emptyList<DiaryEntryEntity>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewId: TextView = itemView.findViewById(R.id.textview_id)
        val textViewDate: TextView = itemView.findViewById(R.id.textview_date)
        val textViewBeforeRating: TextView = itemView.findViewById(R.id.textview_before)
        val textViewAfterRating: TextView = itemView.findViewById(R.id.textview_after)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = moodLogsList[position]

        holder.textViewId.text = current.id.toString()

        // TODO: Display other data from list.
        holder.textViewDate.text = current.getFormattedDateShort()
    }

    internal fun setMoodLogsList(listIn: List<DiaryEntryEntity>) {
        this.moodLogsList = listIn
        notifyDataSetChanged()
    }

    override fun getItemCount() = moodLogsList.size
}