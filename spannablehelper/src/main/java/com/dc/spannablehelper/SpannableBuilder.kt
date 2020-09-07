package com.dc.spannablehelper

import android.content.Context
import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.widget.TextView

/**
 *Create by CZH
 *Create date 2020/1/15
 *Description:构建器便于使用
 */
class SpannableBuilder {
    private lateinit var context: Context
    private var tv: TextView? = null
    private var content: String = ""
    private var textClickListener: TextClickListener? = null
    private val changeItemList = mutableListOf<ChangeItem>()

    fun setTextView(tv: TextView): SpannableBuilder {
        this.tv = tv
        context = tv.context
        return this
    }

    fun setContent(content: String): SpannableBuilder {
        this.content = content
        return this
    }

    /**
     * 单个添加要被操作条目
     * */
    fun addChangeItem(item: ChangeItem): SpannableBuilder {
        changeItemList.add(item)
        return this
    }

    /**
     * 批量添加要被操作条目
     * */
    fun addChangeItems(items: List<ChangeItem>): SpannableBuilder {
        changeItemList.addAll(items)
        return this
    }

    fun setTextClickListener(listener: TextClickListener): SpannableBuilder {
        this.textClickListener = listener
        return this
    }

    fun build() {
        tv?.highlightColor = Color.parseColor("#00ffffff")
        tv?.movementMethod = LinkMovementMethod.getInstance()
        tv?.text = SpannableHelper.changeMultiPart(context, content, changeItemList,textClickListener)
    }

}