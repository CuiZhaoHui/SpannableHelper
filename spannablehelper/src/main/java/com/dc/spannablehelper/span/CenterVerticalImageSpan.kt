package com.dc.spannablehelper.span

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.graphics.drawable.Drawable
import android.text.style.ImageSpan

/**
 * Create by CZH
 * Create date 2020/10/10
 * Description:居中显示的imageSpan
 */
class CenterVerticalImageSpan(drawable: Drawable, verticalAlignment: Int, private val marginLeft: Float,
                              private val marginRight: Float) : ImageSpan(drawable, verticalAlignment) {


    /**
     * update the text line height
     */
    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fontMetricsInt: FontMetricsInt?): Int {
        val drawable = drawable
        val rect = drawable.bounds
        if (fontMetricsInt != null) {
            val fmPaint = paint.fontMetricsInt
            val fontHeight = fmPaint.descent - fmPaint.ascent
            val drHeight = rect.bottom - rect.top
            val centerY = fmPaint.ascent + fontHeight / 2
            fontMetricsInt.ascent = centerY - drHeight / 2
            fontMetricsInt.top = fontMetricsInt.ascent
            fontMetricsInt.bottom = centerY + drHeight / 2
            fontMetricsInt.descent = fontMetricsInt.bottom
        }
        return (rect.right + marginLeft + marginRight).toInt()
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int,
                      paint: Paint) {
        val drawable = drawable
        canvas.save()
        val fmPaint = paint.fontMetricsInt
        val fontHeight = fmPaint.descent - fmPaint.ascent
        val centerY = y + fmPaint.descent - fontHeight / 2
        val transY = centerY - (drawable.bounds.bottom - drawable.bounds.top) / 2
        canvas.translate(x + marginLeft, transY.toFloat())
        drawable.draw(canvas)
        canvas.restore()
    }
}