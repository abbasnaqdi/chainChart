package com.oky2abbas.library.utils

import android.graphics.Color
import com.oky2abbas.library.model.Graph

object FakeGenerator {
    val nameList = listOf("Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    val rangeList = listOf("0", "100", "200", "500")

    fun generate(): List<Graph> {
        val graphList = arrayListOf<Graph>()

        for (i in (1..(1..3).random())) {
            val nodeList = arrayListOf<Float>()

            for (j in (1..6)) {
                nodeList.add((1..500).random().toFloat())
            }

            graphList.add(
                Graph(
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