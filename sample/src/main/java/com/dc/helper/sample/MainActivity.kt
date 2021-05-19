package com.dc.helper.sample

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dc.spannablehelper.ChangeItem
import com.dc.spannablehelper.SpannableHelper
import com.dc.spannablehelper.TextClickListener
import com.dc.spannablehelper.changer.ImageChanger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_main_change.setOnClickListener {
            Toast.makeText(this@MainActivity, "Click Listener", Toast.LENGTH_SHORT).show()
        }

        tv_main_change.setOnLongClickListener {
            Toast.makeText(this@MainActivity, "Long Click Listener", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }

        progress_main_size.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar, p1: Int, p2: Boolean) {

                val currentGreen = Integer.toHexString((255 * (p1.toFloat() / progress_main_size.max)).toInt())

                val textSize = if (p1 < 1) spToPx(1f) else spToPx(p1.toFloat())

                SpannableHelper.with(tv_main_change, tv_main_change.text.toString())
                    .addChangeItem(ImageChanger("[icon]", R.mipmap.ic_launcher, textSize.toInt(),
                        textSize.toInt(), 0f, 50f, click = true, changeAllPlace = false))//icon

                    .addChangeItem(ChangeItem("大小", ChangeItem.Type.SIZE, textSize.toInt()))//text size

                    .addChangeItem(ChangeItem("颜色", ChangeItem.Type.COLOR, Color.parseColor(
                        "#FFFF${if (currentGreen.length == 1) "0${currentGreen}" else currentGreen}00"),
                        true))// text color

                    .setTextClickListener(object : TextClickListener {
                        override fun onTextClick(clickContent: String) {
                            Toast.makeText(this@MainActivity, "Click：$clickContent", Toast.LENGTH_SHORT).show()
                        }
                    }).build()
            }

            override fun onStartTrackingTouch(p0: SeekBar) {
            }

            override fun onStopTrackingTouch(p0: SeekBar) {
            }

        })
    }

    private fun spToPx(sp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)
    }

}
