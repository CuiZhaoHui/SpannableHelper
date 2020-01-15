package com.dc.helper.sample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.SeekBar
import com.dc.spannablehelper.ChangeItem
import com.dc.spannablehelper.SpannableHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress_main_size.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar, p1: Int, p2: Boolean) {

                val currentGreen =
                    Integer.toHexString((255 * (p1.toFloat() / progress_main_size.max)).toInt())

                SpannableHelper.with(tv_main_change, tv_main_change.text.toString())
                    .addChangeItem(
                        ChangeItem(
                            "大小", ChangeItem.Type.SIZE, TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_SP,
                                if (p1 < 1) 1f else p1.toFloat(),
                                resources.displayMetrics
                            ).toInt()
                        )
                    )
                    .addChangeItem(
                        ChangeItem(
                            "颜色",
                            ChangeItem.Type.COLOR,
                            Color.parseColor("#FFFF${if (currentGreen.length == 1) "0${currentGreen}" else currentGreen}00")
                        )
                    )
                    .build()
            }

            override fun onStartTrackingTouch(p0: SeekBar) {
            }

            override fun onStopTrackingTouch(p0: SeekBar) {
            }

        })
    }
}
