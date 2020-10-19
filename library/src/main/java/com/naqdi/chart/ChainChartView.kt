package com.naqdi.chart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.naqdi.chart.model.Line
import com.naqdi.chart.utils.*


class ChainChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : View(
    context, attrs, defStyleAttr
) {
    private var lineList = listOf<Line>()
    private var titleList = listOf<String>()
    private var rangeList = listOf<String>()
    private val splitList = arrayListOf<Float>()


    private val circleRadius = 9f
    private var selectedX = 0f
    private val minLeftMargin = 10f
    private var maxLeftMargin = minLeftMargin * 2
    private val topBottomMargin = 30f
    private var maxGraphTitleSize = 50f
    private var maxNode = 0f


    private var widthVal = 0f
    private var heightVal = 0f


    private val nodePaint = Paint(ANTI_ALIAS_FLAG).apply {
        isAntiAlias = true
        style = Paint.Style.FILL_AND_STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private val linePaint = Paint(ANTI_ALIAS_FLAG).apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
    }

    private val badgePaint = Paint(ANTI_ALIAS_FLAG).apply {
        isAntiAlias = true
        style = Paint.Style.FILL_AND_STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = context.dpToPx(1f)
    }

    private val liteLinePaint = Paint(ANTI_ALIAS_FLAG).apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        color = 0x10000000
    }

    private val strokeLinePaint = Paint(ANTI_ALIAS_FLAG).apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        pathEffect = DashPathEffect(floatArrayOf(10f, 12f), 0f)
        strokeCap = Paint.Cap.ROUND
        color = 0x40000000
    }

    private val textPaint = TextPaint(ANTI_ALIAS_FLAG).apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
    }

    private val textCenterPaint = TextPaint(ANTI_ALIAS_FLAG).apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        textAlign = Paint.Align.CENTER
    }

    init {
        val typeArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.cc_line,
            0, 0
        )

        try {
            typeArray.getDimension(
                R.styleable.cc_line_cc_text_size,
                context.dpToPx(11f)
            ).let {
                textCenterPaint.textSize = it
                textPaint.textSize = it
            }

            typeArray.getDimension(
                R.styleable.cc_line_cc_line_size,
                context.dpToPx(1f)
            ).let {
                linePaint.strokeWidth = it
            }

            typeArray.getDimension(
                R.styleable.cc_line_cc_node_size,
                context.dpToPx(1f)
            ).let {
                nodePaint.strokeWidth = it
            }

            typeArray.getColor(
                R.styleable.cc_line_cc_text_color,
                Color.BLACK
            ).let {
                textCenterPaint.color = it
                textPaint.color = it
            }

            typeArray.getResourceId(
                R.styleable.cc_line_cc_font_family,
                0
            ).let {
                if (it == 0) return@let
                val font = ResourcesCompat.getFont(context, it)
                textCenterPaint.typeface = font
                textPaint.typeface = font
            }

        } finally {
            typeArray.recycle()
        }
    }

    fun setFontFamily(font: Typeface) {
        textCenterPaint.typeface = font
        textPaint.typeface = font
    }

    fun setTextSize(size: Float) {
        textCenterPaint.textSize = context.dpToPx(size)
        textPaint.textSize = context.dpToPx(size)
    }

    fun setLineSize(size: Float) {
        linePaint.strokeWidth = context.dpToPx(size)
    }

    fun setNodeSize(size: Float) {
        nodePaint.strokeWidth = context.dpToPx(size)
    }

    fun setTextColor(color: Int) {
        textCenterPaint.color = color
        textPaint.color = color
    }

/*
 The client can use this method to set data in graphs
 Note: The source instance of using this method is available
 in the `FakeGenerator` object.
 */

    fun setData(
        lineList: List<Line>,
        intervalList: List<String>,
        rangeList: List<String>
    ) {
        this.lineList = lineList
        this.titleList = intervalList
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

        //work only in editable mode
        if (isInEditMode && lineList.isEmpty()) {
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
        lineList.getMaxLine()?.let {
            for (index in it.nodeList.indices) {
                splitList.add(it.getGraphX(index))
            }
        }

        /*
          This code finds the largest text in the range,
           then converts this range to float size
           and is used for padding
         */
        rangeList.map { it.length }.max()?.let {
            maxLeftMargin = minLeftMargin + (it + 110).toFloat()
        }

        /*
          This code finds the largest title in the graph
           and converts this title to a float size
         */
        lineList.map { it.title.getSize() }.max()?.let {
            maxGraphTitleSize = it
        }

        //This code finds the largest node among all graphs
        lineList.flatMap { it.nodeList }.max()?.let {
            maxNode = it
        }

        lineList.forEachIndexed { index, graph ->

            //Draw title badge
            canvas?.drawCircle(
                maxLeftMargin + (index * maxGraphTitleSize), topBottomMargin,
                10f, badgePaint.apply {
                    color = graph.color
                }
            )

            //Draw titles text
            canvas?.drawText(
                graph.title,
                minLeftMargin + maxLeftMargin + (maxGraphTitleSize * index),
                topBottomMargin * 2, textPaint
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
    private fun generateGraph(canvas: Canvas?, line: Line) {
        for (index in (line.nodeList.indices)) {

            val currentX = line.getGraphX(index)
            val currentY = line.getGraphY(index)

            val name = titleList[index]

            //Draw node text
            canvas?.drawText(
                name, currentX, heightVal - topBottomMargin,
                textCenterPaint
            )

            if (index < line.nodeList.size - 1) {
                val nextX = line.getGraphX(index + 1)
                val nextY = line.getGraphY(index + 1)

                //Draw node line
                canvas?.drawLine(
                    currentX, currentY, nextX, nextY,
                    linePaint.apply {
                        color = line.color
                    }
                )
            }

            if (splitList.closestValue(selectedX) == currentX) {

                //Draw selected circle
                canvas?.drawCircle(currentX, currentY, circleRadius,
                    nodePaint.apply {
                        color = line.color
                    })

                //Draw selected vertical line
                canvas?.drawLine(
                    currentX, topBottomMargin * 3, currentX,
                    heightVal - topBottomMargin * 3,
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
    private fun Line.getGraphX(index: Int): Float {
        val w = (widthVal / nodeList.size)
        return (index * w) + (maxLeftMargin)
    }

    //Calculate the height for each node
    private fun Line.getGraphY(index: Int): Float {
        val value = nodeList[index]
        val h = (heightVal - nodeList[index])
        return h - (topBottomMargin * 4)
    }

/*
  Adjust nodes based on height
  If a node is larger than height, all nodes are rendered in height
 */

    private fun Line.round(): Line {
        if (maxNode < heightVal)
            return this

        val nav = (maxNode - (heightVal - topBottomMargin * 7))
        val nav2 = (maxNode / nav)
        nodeList = nodeList.map { it - (it / nav2) }

        return this
    }

    //This method finds the distance between the range elements
    private fun getRangeYVal(index: Int): Float {
        val h = (heightVal / rangeList.size) - (topBottomMargin)
        return (h * index) + (topBottomMargin * 3.5f)
    }
}