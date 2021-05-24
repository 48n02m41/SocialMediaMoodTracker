package dev48n02m41.socialmediamoodtracker.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import dev48n02m41.socialmediamoodtracker.R
import java.util.*
import kotlin.collections.ArrayList

private lateinit var chart: PieChart
class PieChartTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart_test)

        chartInit()
        chartDataInit()
    }

    private fun chartDataInit() {

        val averageMoodRating: MutableList<PieEntry> = arrayListOf()
        averageMoodRating.add(PieEntry(3f, "Twitter"))
        averageMoodRating.add(PieEntry(2f, "Facebook"))
        averageMoodRating.add(PieEntry(1f, "SnapChat"))

        val dataSet: PieDataSet = PieDataSet(averageMoodRating, "Mood Ratings")
        dataSet.colors = ColorTemplate.createColors(ColorTemplate.MATERIAL_COLORS)
        val data: PieData = PieData(dataSet)

        chart.data = data
    }

    private fun chartInit() {
        chart = findViewById(R.id.piechart)
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5F, 10F, 5F, 5F);

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0F);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);
        chart.animateY(1400, Easing.EaseInOutQuad);

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTextSize(12f);
    }
}