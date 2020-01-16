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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress_main_size.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar, p1: Int, p2: Boolean) {

                val currentGreen =
                    Integer.toHexString((255 * (p1.toFloat() / progress_main_size.max)).toInt())

                val textSize = if (p1 < 1) spToPx(1f) else spToPx(p1.toFloat())

                SpannableHelper.with(tv_main_change, tv_main_change.text.toString())
                    .addChangeItem(ChangeItem("大小", ChangeItem.Type.SIZE, textSize.toInt()))
                    .addChangeItem(
                        ChangeItem(
                            "颜色",
                            ChangeItem.Type.COLOR,
                            Color.parseColor("#FFFF${if (currentGreen.length == 1) "0${currentGreen}" else currentGreen}00")
                            , true
                        )
                    ).setTextClickListener(object : TextClickListener {
                        override fun onTextClick(clickContent: String) {
                            Toast.makeText(
                                this@MainActivity,
                                "点击内容：$clickContent",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                    .build()
            }

            override fun onStartTrackingTouch(p0: SeekBar) {
            }

            override fun onStopTrackingTouch(p0: SeekBar) {
            }

        })
    }

    private fun spToPx(sp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp, resources.displayMetrics
        )
    }

}
