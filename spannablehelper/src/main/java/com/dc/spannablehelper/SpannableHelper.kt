package com.dc.spannablehelper

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.TextUtils
import android.text.style.*
import android.view.View
import android.widget.TextView

/**
 *Create by CZH
 *Create date 2020/1/15
 *Description: 工具类
 */

object SpannableHelper {


    fun with(tv: TextView, content: String): SpannableBuilder {
        val builder = SpannableBuilder()
        builder.setContent(content)
        return builder.setTextView(tv)
    }


    /**
     * 改变某一部分字体大小
     * @param content   原串
     * @param changeStr 要被改变的串
     * @param size      要被改变的大小单位PX
     */
    fun changePartTextSize(content: String, changeStr: String, size: Int): SpannableString {
        val start = content.indexOf(changeStr)
        val end = start + changeStr.length
        val span = SpannableString(content)
        if (start == -1 || end == -1)
            return span
        span.setSpan(AbsoluteSizeSpan(size), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        return span
    }

    /**
     * 改变多部分字体大小
     * @param content   原串
     * @param changeList 要被改变的串集合
     * @param sizeList      要被改变的大小集合 单位PX
     */
    fun changeMultiPartTextSize(
        content: String,
        changeList: List<String>,
        sizeList: List<Int>
    ): SpannableString {
        val span = SpannableString(content)
        if (changeList.isEmpty() || sizeList.isEmpty() || changeList.size != sizeList.size)
            return span
        changeList.forEachIndexed { index, changeStr ->
            val start = content.indexOf(changeStr)
            val end = start + changeStr.length
            if (start != -1 && end != -1)
                span.setSpan(
                    AbsoluteSizeSpan(sizeList[index]),
                    start,
                    end,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
        }
        return span
    }

    /**
     * 改变某一部分字体颜色
     * @param content   原串
     * @param changeStr 要被改变的串
     * @param color     要被改变的颜色值
     */
    fun changePartTextColor(content: String, changeStr: String, color: Int): SpannableString {
        val start = content.indexOf(changeStr)
        val end = start + changeStr.length
        val span = SpannableString(content)
        if (start == -1 || end == -1)
            return span
        span.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        return span
    }

    /**
     * 改变多部分字体大小
     * @param content   原串
     * @param changeList 要被改变的串集合
     * @param colorList  要被改变的颜色集合
     */
    fun changeMultiPartTextColor(
        content: String,
        changeList: List<String>,
        colorList: List<Int>
    ): SpannableString {
        val span = SpannableString(content)
        if (changeList.isEmpty() || colorList.isEmpty() || changeList.size != colorList.size)
            return span
        changeList.forEachIndexed { index, changeStr ->
            val start = content.indexOf(changeStr)
            val end = start + changeStr.length
            if (start != -1 && end != -1)
                span.setSpan(
                    ForegroundColorSpan(colorList[index]),
                    start,
                    end,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
        }
        return span
    }

    /**
     * 操作多种信息（可多处同时操作）
     * */
    fun changeMultiPart(
        context: Context,
        content: String?,
        changeItems: MutableList<ChangeItem>, textClickListener: TextClickListener? = null
    ): SpannableString {

        val spannable = SpannableString(content)

        if (TextUtils.isEmpty(content) || changeItems.isEmpty())
            return spannable

        changeItems.forEach {
            val changeStr = it.partStr

            val startIndex = content!!.indexOf(changeStr)
            val endIndex = startIndex + changeStr.length

            if (startIndex != -1 && endIndex != -1) {
                if (it.click) {
                    val clickableSpan = object : ClickableSpan() {
                        override fun onClick(view: View) {
                            textClickListener?.onTextClick(changeStr)
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            super.updateDrawState(ds)
                            ds.isUnderlineText = false
                        }
                    }
                    spannable.setSpan(
                        clickableSpan,
                        startIndex,
                        endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                when (it.type) {
                    ChangeItem.Type.COLOR -> {
                        spannable.setSpan(
                            ForegroundColorSpan(it.value),
                            startIndex,
                            endIndex,
                            SpannableString.SPAN_INCLUSIVE_EXCLUSIVE
                        )
                    }
                    ChangeItem.Type.SIZE -> {
                        spannable.setSpan(
                            AbsoluteSizeSpan(it.value),
                            startIndex,
                            endIndex,
                            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                        )
                    }
                    ChangeItem.Type.ICON -> {
                        spannable.setSpan(
                            ImageSpan(context, it.value, DynamicDrawableSpan.ALIGN_BASELINE)
                            , startIndex, endIndex, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                    else -> {

                    }
                }
            }

        }
        return spannable
    }
}