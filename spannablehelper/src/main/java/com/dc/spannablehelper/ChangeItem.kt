package com.dc.spannablehelper

/**
 *Create by CZH
 *Create date 2020/1/15
 *Description:被操作部分信息
 * @param partStr 被改变部分
 * @param type 操作类型
 * @param value 颜色值/字体大小值/图片id
 * @param click 该部分是否要监听点击
 */
class ChangeItem(val partStr: String, val type: Type, val value: Int, val click: Boolean = false) {

    enum class Type {
        SIZE, COLOR, ICON
    }


}