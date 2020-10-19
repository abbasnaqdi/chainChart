package com.naqdi.chart.model

import androidx.annotation.ColorInt


data class Line(
    val title: String,
    @ColorInt val color: Int,
    var nodeList: List<Float>
)