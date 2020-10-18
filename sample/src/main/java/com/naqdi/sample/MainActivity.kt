package com.naqdi.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oky2abbas.library.utils.FakeGenerator
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        btnGenerate.setOnClickListener {
            grfView1.setData(
                FakeGenerator.generate(),
                FakeGenerator.nameList, FakeGenerator.rangeList
            )
        }
    }
}