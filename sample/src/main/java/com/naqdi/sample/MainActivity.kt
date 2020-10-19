package com.naqdi.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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


                setData(
                    FakeGenerator.generate(),
                    FakeGenerator.nameList, FakeGenerator.rangeList
                )
            }
        }
    }
}