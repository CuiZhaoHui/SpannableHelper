package com.dc.spannablehelper.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import org.jetbrains.annotations.NotNull;

/**
 * Create by CZH
 * Create date 2020/10/10
 * Description:居中显示的imageSpan
 */
public class CenterVerticalImageSpan extends ImageSpan {
    private float marginLeft = 0;
    private float marginRight = 0;

    public CenterVerticalImageSpan(Drawable drawable, int verticalAlignment, float marginLeft, float marginRight) {
        super(drawable, verticalAlignment);
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
    }



    /**
     * update the text line height
     */
    @Override
    public int getSize(@NotNull Paint paint, CharSequence text, int start, int end,
                       Paint.FontMetricsInt fontMetricsInt) {
        Drawable drawable = getDrawable();
        Rect rect = drawable.getBounds();
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
            int fontHeight = fmPaint.descent - fmPaint.ascent;
            int drHeight = rect.bottom - rect.top;
            int centerY = fmPaint.ascent + fontHeight / 2;

            fontMetricsInt.ascent = centerY - drHeight / 2;
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = centerY + drHeight / 2;
            fontMetricsInt.descent = fontMetricsInt.bottom;
        }
        return (int) (rect.right + marginLeft + marginRight);
    }


    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end,
                     float x, int top, int y, int bottom, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
        int fontHeight = fmPaint.descent - fmPaint.ascent;
        int centerY = y + fmPaint.descent - fontHeight / 2;
        int transY = centerY - (drawable.getBounds().bottom - drawable.getBounds().top) / 2;
        canvas.translate(x + marginLeft, transY);
        drawable.draw(canvas);
        canvas.restore();
    }
}
