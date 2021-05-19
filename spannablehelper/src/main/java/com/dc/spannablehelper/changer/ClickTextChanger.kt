package com.dc.spannablehelper.changer

import com.dc.spannablehelper.ChangeItem

/**
 *Create by CZH
 *Create date 2021/1/12
 *Description:同时处理文字多个变化部分
 */
class ClickTextChanger : ChangeItem {

    constructor(partStr: String, changeAllPlace: Boolean = true) : super(partStr, Type.NONE,
        0, true, changeAllPlace)

    constructor(partStrList: ArrayList<String>, changeAllPlace: Boolean = true) : super(partStrList, Type.NONE,
        arrayListOf(), true, changeAllPlace)

}