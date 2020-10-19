package com.naqdi.chart.utils

import android.graphics.Color
import com.naqdi.chart.model.Line

object FakeGenerator {
    val nameList = listOf("Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    val rangeList = listOf("0-1K", "100K", "200K", "500K")

    fun generate(): List<Line> {
        val graphList = arrayListOf<Line>()

        for (i in (1..(1..3).random())) {
            val nodeList = arrayListOf<Float>()

            for (j in (1..6)) {
                nodeList.add((1..500).random().toFloat())
            }

            graphList.add(
                Line(
                    "Node ${(1..9).random()}", getColorValue(i), nodeList
                )
            )
        }

        return graphList
    }

    private fun getColorValue(index: Int) = when (index) {
        1 -> Color.BLUE; 2 -> Color.GREEN;
        3 -> Color.GRAY;
        else -> Color.DKGRAY
    }
}