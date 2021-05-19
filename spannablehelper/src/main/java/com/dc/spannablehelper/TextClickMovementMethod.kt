package com.dc.spannablehelper

import android.annotation.SuppressLint
import android.text.Selection
import android.text.Spannable
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewParent
import android.widget.TextView

/**
 *Create by CZH
 *Create date 2021/1/12
 *Description:
 */
class TextClickMovementMethod : View.OnTouchListener {

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (!::longClickRunnable.isInitialized) {
            longClickRunnable = LongClickRunnable(v)
        }
        val tv = v as TextView
        val action = event.action
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
            var x = event.x.toInt()
            var y = event.y.toInt()

            x -= tv.totalPaddingLeft
            y -= tv.totalPaddingTop
            x += tv.scrollX
            y += tv.scrollY
            val layout = tv.layout
            val line = layout.getLineForVertical(y)
            val off = layout.getOffsetForHorizontal(line, x.toFloat())
            val spannable = Spannable.Factory.getInstance().newSpannable(tv.text)
            val link = spannable.getSpans(off, off, ClickableSpan::class.java)
            if (link.isNotEmpty()) {
                when (action) {
                    MotionEvent.ACTION_UP -> {
                        /*单击*/
                        v.removeCallbacks(longClickRunnable)
                        link[0].onClick(tv)
                    }
                    MotionEvent.ACTION_DOWN -> {
                        /*长按*/
                        v.postDelayed(longClickRunnable, ViewConfiguration.getLongPressTimeout().toLong())
                    }
                    else -> {
                        /*其它*/
                        v.removeCallbacks(longClickRunnable)
                        Selection.setSelection(spannable, spannable.getSpanStart(link[0]), spannable.getSpanEnd(link[0]))
                    }
                }
                return true
            } else {
                Selection.removeSelection(spannable)
            }
        }
        return false
    }

    private lateinit var longClickRunnable: LongClickRunnable

    private inner class LongClickRunnable(val v: View) : Runnable {
        override fun run() {
            var consumed = v.performLongClick()
            while (!consumed) {
                val parentView: ViewParent = v.parent ?: break
                if (parentView is View) {
                    consumed = parentView.performLongClick()
                }
            }
        }
    }

    companion object {
        val instance by lazy(LazyThreadSafetyMode.NONE) {
            TextClickMovementMethod()
        }
    }

}