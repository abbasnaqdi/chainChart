package com.oky2abbas.library.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.oky2abbas.library.ext.closestValue
import com.oky2abbas.library.ext.dpToPx
import com.oky2abbas.library.ext.getMaxGraph
import com.oky2abbas.library.ext.getSize
import com.oky2abbas.library.model.Graph
import com.oky2abbas.library.utils.FakeGenerator

/*
  @author: abbas naqdi (naqdi)
  - All the following code was written by @naqdi without any copying
  - Use of this source code is not permitted without permission
  - This source was sent to https://zelkaa.com
 */

class ChainChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : View(
    context, attrs, defStyleAttr
) {

    private var graphList = listOf<Graph>()
    private var nameList = listOf<String>()
    private var rangeList = listOf<String>()
    private val splitList = arrayListOf<Float>()

    private val circleRadius = 9f
    private var selectedX = 0f
    private val minLeftMargin = 10f
    private var maxLeftMargin = minLeftMargin * 2
    private val topMargin = 30f
    private val bottomMargin = 30f
    private val textSize = 11f
    private var maxGraphTitleSize = 50f
    private var maxNode = 0f


    private var widthVal = 0f
    private var heightVal = 0f

    private val circlePaint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val linePaint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 3f
    }

    private val badgePaint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 5f
    }

    private val liteLinePaint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 0.09f
    }

    private val strokeLinePaint = Paint().apply {
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 4f
        pathEffect = DashPathEffect(floatArrayOf(10f, 12f), 0f)
        strokeCap = Paint.Cap.BUTT
        color = 0x20000000
    }

    private val textPaint = TextPaint().apply {
        isAntiAlias = true
        textSize = getContext().dpToPx(textSize)
        color = Color.BLACK
    }

    private val textCenterPaint = TextPaint().apply {
        isAntiAlias = true
        textSize = getContext().dpToPx(textSize)
        color = Color.BLACK
        textAlign = Paint.Align.CENTER
    }

    /*
     The client can use this method to set data in graphs
     Note: The source instance of using this method is available
     in the `FakeGenerator` object.
     */

    fun setData(
        graphList: List<Graph>,
        nameList: List<String>,
        rangeList: List<String>
    ) {
        this.graphList = graphList
        this.nameList = nameList
        this.rangeList = rangeList

        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        widthVal = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        heightVal = MeasureSpec.getSize(heightMeasureSpec).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //
        if (graphList.isEmpty()) {
            setData(
                FakeGenerator.generate(),
                FakeGenerator.nameList,
                FakeGenerator.rangeList
            )

            return
        }

        /*
          This code finds the largest graph based on the number of nodes,
           then divides it based on this graph.
         */
        graphList.getMaxGraph()?.let {
            for (index in it.nodeList.indices) {
                splitList.add(it.getGraphX(index))
            }
        }

        /*
          This code finds the largest text in the range,
           then converts this range to float size
           and is used for padding
         */
        rangeList.map { it.length }.maxOrNull()?.let {
            maxLeftMargin = minLeftMargin + (it + 110).toFloat()
        }

        /*
          This code finds the largest title in the graph
           and converts this title to a float size
         */
        graphList.map { it.title.getSize() }.maxOrNull()?.let {
            maxGraphTitleSize = it
        }

        //This code finds the largest node among all graphs
        graphList.flatMap { it.nodeList }.max()?.let {
            maxNode = it
        }

        graphList.forEachIndexed { index, graph ->

            //Draw title badge
            canvas?.drawCircle(
                maxLeftMargin + (index * maxGraphTitleSize), topMargin,
                10f, badgePaint.apply {
                    color = graph.color
                }
            )

            //Draw titles text
            canvas?.drawText(
                graph.title,
                minLeftMargin + maxLeftMargin + (maxGraphTitleSize * index),
                topMargin * 2, textPaint
            )

            rangeList.reversed().forEachIndexed { index, value ->

                //Draw range text
                canvas?.drawText(
                    value, minLeftMargin,
                    getRangeYVal(index), textPaint
                )

                //Draw range line
                canvas?.drawLine(
                    maxLeftMargin,
                    getRangeYVal(index),
                    widthVal,
                    getRangeYVal(index),
                    liteLinePaint
                )
            }


            //round all value for compat by height
            generateGraph(canvas, graph.round())
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun generateGraph(canvas: Canvas?, graph: Graph) {
        for (index in (graph.nodeList.indices)) {

            val currentX = graph.getGraphX(index)
            val currentY = graph.getGraphY(index)

            val name = nameList[index]

            //Draw node text
            canvas?.drawText(
                name, currentX, heightVal - topMargin,
                textCenterPaint
            )

            if (index < graph.nodeList.size - 1) {
                val nextX = graph.getGraphX(index + 1)
                val nextY = graph.getGraphY(index + 1)

                //Draw node line
                canvas?.drawLine(
                    currentX, currentY, nextX, nextY,
                    linePaint.apply {
                        color = graph.color
                    }
                )
            }

            if (splitList.closestValue(selectedX) == currentX) {

                //Draw selected circle
                canvas?.drawCircle(currentX, currentY, circleRadius,
                    circlePaint.apply {
                        color = graph.color
                    })

                //Draw selected vertical line
                canvas?.drawLine(
                    currentX, topMargin * 3, currentX,
                    heightVal - topMargin * 3,
                    strokeLinePaint
                )
            }

            setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP
                    || event.action == MotionEvent.ACTION_MOVE
                ) {
                    selectedX = event.x
                    invalidate()
                }

                return@setOnTouchListener true
            }
        }
    }

    //Calculate the width for each node
    private fun Graph.getGraphX(index: Int): Float {
        val w = (widthVal / nodeList.size)
        return (index * w) + (maxLeftMargin)
    }

    //Calculate the height for each node
    private fun Graph.getGraphY(index: Int): Float {
        val value = nodeList[index]
        val h = (heightVal - nodeList[index])
        return h - (topMargin * 4)
    }

    /*
      Adjust nodes based on height
      If a node is larger than height, all nodes are rendered in height
     */

    private fun Graph.round(): Graph {
        if (maxNode < heightVal)
            return this

        val nav = (maxNode - (heightVal - topMargin * 7))
        val nav2 = (maxNode / nav)
        nodeList = nodeList.map { it - (it / nav2) }

        return this
    }

    //This method finds the distance between the range elements
    private fun getRangeYVal(index: Int): Float {
        val h = (heightVal / rangeList.size) - (topMargin)
        return (h * index) + (topMargin * 3.5f)
    }
}