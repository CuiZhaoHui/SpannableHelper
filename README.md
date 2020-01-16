# SpannableHelper
----
## 快速实现文本部分变色、改变大小、部分点击的轻量工具类
---
![](https://raw.githubusercontent.com/CuiZhaoHui/SpannableHelper/master/gif/RECORD.gif)
----

#### usage
```
SpannableHelper.with(textView, content)
    .addChangeItem(ChangeItem(changeStr, ChangeItem.Type.SIZE, textSize))
    .addChangeItem(ChangeItem(changeStr,ChangeItem.Type.COLOR,textColor,isClickAble))
    .setTextClickListener(object : TextClickListener {
        override fun onTextClick(clickContent: String) {
            //TODO do something
        }
    })
    .build()
```
