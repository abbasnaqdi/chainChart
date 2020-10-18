package com.oky2abbas.library.ext

import android.content.Context
import android.util.TypedValue
import com.oky2abbas.library.model.Graph


internal fun Context.dpToPx(dp: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics
)

internal fun String.getSize(): Float {
    return length * 25f
}

internal fun List<Float>.closestValue(value: Float): Float? {
    return minBy { kotlin.math.abs(value - it) }
}

internal fun List<Graph>.getMaxGraph(): Graph? {
    return maxBy { it.nodeList.size }
}