package com.nanchen.ncprocessview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.math.min

/**
 * Author: nanchen
 * Email: liushilin2251@gmail.com
 * Date: 2019-11-25 18:07
 */
class NCProcessView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val DEFAULT_FINISHED_COLOR = Color.parseColor("#0ccc8c")
    private val DEFAULT_UNFINISHED_COLOR = Color.parseColor("#c3c6cb")
    private val DEFAULT_FINISHED_TV_COLOR = Color.parseColor("#ffffff")
    private val DEFAULT_UNFINISHED_TV_COLOR = Color.parseColor("#c3c6cb")

    private val finishedPaint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private val unFinishedPaint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private val finishedTvPaint by lazy { TextPaint(Paint.ANTI_ALIAS_FLAG) }
    private val unFinishedTvPaint by lazy { TextPaint(Paint.ANTI_ALIAS_FLAG) }
    private var finishedColor = DEFAULT_FINISHED_COLOR
    private var unFinishedColor = DEFAULT_UNFINISHED_COLOR
    private var finishedTvColor = DEFAULT_FINISHED_TV_COLOR
    private var unFinishedTvColor = DEFAULT_UNFINISHED_TV_COLOR
    private var tvSize = dp2px(12F)
    private var strokeWidth = dp2px(12F)
    private var circleRadius = dp2px(8F)
    var processData = intArrayOf(0, 0, 0, 0, 0)   // 进程状态
        set(value) {
            field = value
            invalidate()
        }

    private var strokePadding = dp2px(1F)
    private val tvHeightOffset: Float
    private val defaultWidth: Int
        get() = (circleRadius * 2 * processData.size + (strokeWidth + strokePadding * 2) * (processData.size - 1)).toInt() + paddingLeft + paddingRight

    private val defaultHeight: Int
        get() = (circleRadius * 2).toInt()

    init {
        context.obtainStyledAttributes(attrs, R.styleable.NCProcessView).run {
            tvSize = getDimensionPixelSize(
                R.styleable.NCProcessView_pv_tv_size,
                dp2px(14f).toInt()
            ).toFloat()
            finishedColor =
                getColor(R.styleable.NCProcessView_pv_finished_color, DEFAULT_FINISHED_COLOR)
            unFinishedColor = getColor(
                R.styleable.NCProcessView_pv_unfinished_color,
                DEFAULT_UNFINISHED_COLOR
            )
            finishedTvColor = getColor(
                R.styleable.NCProcessView_pv_finished_tv_color,
                DEFAULT_FINISHED_TV_COLOR
            )
            unFinishedTvColor = getColor(
                R.styleable.NCProcessView_pv_unfinished_tv_color,
                DEFAULT_UNFINISHED_TV_COLOR
            )
            val resId = getResourceId(R.styleable.NCProcessView_pv_data, 0)
            if (resId != 0) {
                processData = resources.getIntArray(resId)
            }
            strokeWidth =
                getDimensionPixelSize(
                    R.styleable.NCProcessView_pv_stroke_width,
                    dp2px(12F).toInt()
                ).toFloat()
            strokePadding = getDimensionPixelSize(R.styleable.NCProcessView_pv_stroke_padding, dp2px(1f).toInt()).toFloat()
            circleRadius =
                getDimensionPixelSize(R.styleable.NCProcessView_pv_circle_radius, dp2px(8F).toInt()).toFloat()
            recycle()
        }
        finishedPaint.apply {
            color = finishedColor
            style = Paint.Style.FILL
        }
        unFinishedPaint.apply {
            color = unFinishedColor
            style = Paint.Style.STROKE
        }
        finishedTvPaint.apply {
            color = finishedTvColor
            textSize = tvSize
        }
        unFinishedTvPaint.apply {
            color = unFinishedTvColor
            textSize = tvSize
        }
        tvHeightOffset = -(finishedTvPaint.ascent() + finishedTvPaint.descent()) * 0.5f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(
            getExpectSize(defaultWidth, widthMeasureSpec), getExpectSize(
                defaultHeight,
                heightMeasureSpec
            )
        )
    }

    private fun getExpectSize(size: Int, measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        return when (specMode) {
            MeasureSpec.EXACTLY -> specSize
            MeasureSpec.UNSPECIFIED -> size
            MeasureSpec.AT_MOST -> min(size, specSize)
            else -> {
                size
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        val left = paddingLeft
        val top = paddingTop
        // 绘制
        for (i in processData.indices) {
            val cx =
                left + circleRadius * 2 * i + circleRadius + (strokeWidth + strokePadding * 2) * i
            if (processData[i] == 1) {
                canvas.drawCircle(cx, top + circleRadius, circleRadius, finishedPaint)
            } else {
                canvas.drawCircle(cx, top + circleRadius, circleRadius, unFinishedPaint)
            }
            // 绘制文本
            val content = (i + 1).toString()
            val tvWidth = finishedTvPaint.measureText(content)
            if (processData[i] == 1) {    // 已完成
                canvas.drawText(
                    content,
                    cx - 0.5f * tvWidth,
                    measuredHeight * 0.5f + tvHeightOffset,
                    finishedTvPaint
                )
                // 绘制绿色线条
                if (i > 0) {
                    canvas.drawLine(
                        cx - circleRadius - strokeWidth - strokePadding,
                        circleRadius,
                        cx - circleRadius - strokePadding,
                        circleRadius,
                        finishedPaint
                    )
                }
            } else {  // 未完成
                canvas.drawText(
                    content,
                    cx - 0.5f * tvWidth,
                    measuredHeight * 0.5f + tvHeightOffset,
                    unFinishedTvPaint
                )
                // 绘制灰色线条
                if (i > 0) {
                    canvas.drawLine(
                        cx - circleRadius - strokeWidth - strokePadding,
                        circleRadius,
                        cx - circleRadius - strokePadding,
                        circleRadius,
                        unFinishedPaint
                    )
                }
            }


        }
    }

    private fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.applicationContext.resources.displayMetrics
        )
    }
}