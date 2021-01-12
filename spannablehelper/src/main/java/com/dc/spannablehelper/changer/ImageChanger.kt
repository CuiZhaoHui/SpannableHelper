package com.dc.spannablehelper.changer

import com.dc.spannablehelper.ChangeItem

/**
 *Create by CZH
 *Create date 2021/1/12
 *Description:
 * @param
 */
class ImageChanger(
    partStr: String, iconResourceId: Int,
    val iconWidth: Int, val iconHeight: Int, val leftMargin: Float, val rightMargin: Float, click: Boolean = false,
    changeAllPlace: Boolean = true
) : ChangeItem(partStr, Type.ICON, iconResourceId, click, changeAllPlace)