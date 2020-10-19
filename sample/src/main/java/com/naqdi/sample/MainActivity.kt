package com.naqdi.sample

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naqdi.chart.model.Line
import com.naqdi.chart.utils.FakeGenerator
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        btnGenerate.setOnClickListener {
            chainChartView.apply {

                //example

//                setLineSize(3f)
//                setTextSize(13f)
//                setTextColor(Color.GRAY)
//                setNodeSize(8F)
//                setFontFamily(Typeface.DEFAULT_BOLD)


//                val intervalList = listOf("Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
//                val rangeList = listOf("0-1K", "100K", "200K", "500K")
//                val lineList = arrayListOf<Line>().apply {
//                    add(Line("Line 1", Color.BLUE, listOf(10f, 280f, 88f, 70f, 23f, 33f)))
//                    add(Line("Line 2", Color.RED, listOf(300f, 40f, 38f, 180f, 403f, 201f)))
//                }
//
//                setData(lineList, intervalList, rangeList)

                setData(
                    FakeGenerator.generate(),
                    FakeGenerator.nameList, FakeGenerator.rangeList
                )
            }
        }
    }
}