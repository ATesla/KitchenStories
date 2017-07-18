package com.shide56.kitchenstories.helper.square.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.TypedValue;

import com.squareup.picasso.Transformation;

/**
 * 设置圆角矩形
 */
public class RoundRectTransformation implements Transformation {
    private final float dimension;
    private final Canvas canvas;
    private final Paint paint;

    public RoundRectTransformation(Context context) {
        canvas = new Canvas();
        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        dimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7.5f, context.getResources().getDisplayMetrics());
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(output);

        canvas.drawRoundRect(new RectF(new Rect(0, 0, width, height)), dimension, dimension, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        paint.setXfermode(null);

        if (!source.isRecycled()) {
            source.recycle();
        }

        return output;
    }

    @Override
    public String key() {
        return getClass().getName();
    }
}
