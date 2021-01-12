package com.dc.spannablehelper

/**
 *Create by CZH
 *Create date 2020/1/15
 *Description:被操作部分信息
 */
open class ChangeItem  {

    /**颜色值/字体大小值/图片id*/
    val valueList: ArrayList<Int>
    /**是否要监听点击*/
    val click:Boolean
    /**操作类型*/
    val changeType:Type
    /**是否所有相同与 partStr 部分都做更改*/
    val changeAllPlace:Boolean
    /**被改变部分集合*/
    val changePartList :ArrayList<String>

    constructor(partStr: String, changeType: Type, value: Int
                , click: Boolean = false,changeAllPlace:Boolean = true){
        this.changeType = changeType
        this.click = click
        this.valueList = arrayListOf(value)
        this.changeAllPlace = changeAllPlace
        changePartList = arrayListOf(partStr)
    }

    constructor(partStrList: ArrayList<String>, changeType: Type, valueList: ArrayList<Int>
                , click: Boolean = false,changeAllPlace:Boolean = true){
        this.changeType = changeType
        this.click = click
        this.valueList = valueList
        this.changeAllPlace = changeAllPlace
        this.changePartList = partStrList
    }


    enum class Type {
        SIZE,//字体大小
        COLOR,//字体颜色
        ICON,//文字变图片
        NONE//不做任何处理，只想单独加点击时可用
    }


}