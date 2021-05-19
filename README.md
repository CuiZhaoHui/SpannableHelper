# SpannableHelper

快速实现文本部分变色、改变大小、部分点击的轻量工具类

## Screenshot
![](https://raw.githubusercontent.com/CuiZhaoHui/SpannableHelper/master/gif/RECORD.gif)


## usage
Step1:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step2:
```
implementation 'com.github.CuiZhaoHui:SpannableHelper:1.0.3'
```
Step3:
```kotlin
SpannableHelper.with(textView, content)
    .addChangeItem(ChangeItem(changeStr, ChangeItem.Type.SIZE, textSize))//Change text size
    .addChangeItem(ChangeItem(changeStr,ChangeItem.Type.COLOR,textColor,isClickAble))// Change text color
    .addChangeItem(ImageChanger(changeStr, [Image resource id], [image width],
                        [image height], [left margin], [right margin], isClickAble, changeAllPlace))//Change text to icon
    .setTextClickListener(object : TextClickListener {
        override fun onTextClick(clickContent: String) {
            //TODO do something
        }
    })
    .build()//Do not forget build()!~~
```
