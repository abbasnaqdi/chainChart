package com.naqdi.chart.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import com.naqdi.chart.model.Line


internal fun Context.dpToPx(dp: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics
)

internal fun String.getSize(): Float {
    return length * 25f
}

internal fun List<Float>.closestValue(value: Float): Float? {
    return minByOrNull { kotlin.math.abs(value - it) }
}

internal fun List<Line>.getMaxLine(): Line? {
    return maxByOrNull { it.nodeList.size }
}