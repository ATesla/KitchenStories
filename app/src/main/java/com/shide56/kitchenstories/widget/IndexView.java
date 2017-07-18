package com.shide56.kitchenstories.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 高仿QQ联系人列表索引
 */

public class IndexView extends View {
    private final static String TAG = IndexView.class.getSimpleName();

    private final static String[] LETTERS = {
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};
    private final static RectF[] RECTS = {
            new RectF(), new RectF(), new RectF(), new RectF(), new RectF(), new RectF(), new RectF(),
            new RectF(), new RectF(), new RectF(), new RectF(), new RectF(), new RectF(), new RectF(),
            new RectF(), new RectF(), new RectF(), new RectF(), new RectF(), new RectF(),
            new RectF(), new RectF(), new RectF(), new RectF(), new RectF(), new RectF()};
    private Paint paint;
    private int measuredWidth;
    private float cellHeight;
    private int measuredHeight;
    private int lastIndex = -1;
    private int currentIndex = -1;
    private WindowManager wm;
    private WindowManager.LayoutParams params;
    private TextView toast;

    public IndexView(Context context) {
        this(context, null);
    }

    public IndexView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        float v = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 26, context.getResources().getDisplayMetrics());
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(v);
        paint.setTextAlign(Paint.Align.CENTER);

        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        params = getWindowLayoutParams();
        toast = getTextView();
    }

    @NonNull
    private WindowManager.LayoutParams getWindowLayoutParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.windowAnimations = 0;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.setTitle("Toast");
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        return params;
    }

    @NonNull
    private TextView getTextView() {
        Drawable drawable = getDrawable();
        float size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 26, getDisplayMetrics());
        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getDisplayMetrics());
        TextView toast = new TextView(getContext());
        toast.setWidth(value);
        toast.setHeight(value);
//        toast.setPadding(value, value, value, value);
        toast.setTextColor(Color.WHITE);
        toast.setTextSize(size);
        toast.setGravity(Gravity.CENTER);
//        toast.setBackgroundColor(Color.GRAY);
//        toast.setBackground(drawable);
        toast.setBackgroundDrawable(drawable);
        return toast;
    }

    @NonNull
    private Drawable getDrawable() {
        int conter = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getDisplayMetrics());
        float[] outerRadii = {conter, conter, conter, conter, conter, conter, conter, conter};
        RoundRectShape shape = new RoundRectShape(outerRadii, null, null);
        ShapeDrawable drawable = new ShapeDrawable(shape);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.setAlpha(128);
        return drawable;
    }

    private DisplayMetrics getDisplayMetrics() {
        return getContext().getResources().getDisplayMetrics();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
        cellHeight = (float) measuredHeight / 26f;
/*        for (int i = 0; i < RECTS.length; i++) {
            RectF rectF = RECTS[i];
            float left = ((float) measuredWidth - paint.measureText(LETTERS[i])) / 2.0f;
            float top = i * cellHeight;
            float right = left + paint.measureText(LETTERS[i]);
            float bottom = top + cellHeight;
            rectF.set(left, top, right, bottom);
            Log.e(TAG, rectF.toString());
        }*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.DKGRAY);
        for (int i = 0; i < LETTERS.length; i++) {
            String letter = LETTERS[i];
            float x = ((float) measuredWidth - paint.measureText(letter)) / 2.0f;
            float y = (i + 1) * cellHeight;
            paint.setColor(i == currentIndex ? Color.GRAY : Color.WHITE);
            canvas.drawText(letter, x, y, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handled;
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                currentIndex = (int) (event.getY() / cellHeight);
                if (event.getY() >= 0 && event.getY() <= measuredHeight) {
                    lastIndex = currentIndex;
                    if (null != listener) {
                        listener.onLetterShow(LETTERS[currentIndex]);
//                        Log.e(TAG, "currentPositio" + LETTERS[currentIndex]);
                    }
                    wm.addView(toast, params);
                    toast.setText(LETTERS[currentIndex]);
                }
                handled = true;
                break;
            case MotionEvent.ACTION_MOVE:
                currentIndex = (int) (event.getY() / cellHeight);
                if (event.getY() >= 0 && event.getY() <= measuredHeight) {
                    if (lastIndex != currentIndex) {
                        lastIndex = currentIndex;
                        if (null != listener) {
                            listener.onLetterShow(LETTERS[currentIndex]);
//                            Log.e(TAG, "lastIndex||=" + LETTERS[currentIndex]);
                        }
                    }
                    toast.setText(LETTERS[currentIndex]);
                }
                handled = true;
                break;
            case MotionEvent.ACTION_UP:
                lastIndex = -1;
                currentIndex = -1;
                if (null != listener) {
                    listener.onLetterDismiss();
                }
                wm.removeView(toast);
                handled = true;
                break;
            default:
                lastIndex = -1;
                currentIndex = -1;
                if (null != listener) {
                    listener.onLetterDismiss();
                }
                wm.removeView(toast);
                handled = false;
        }
        invalidate();
        return handled || super.onTouchEvent(event);
    }

    public String getLetter(int position) {
        return LETTERS[position];
    }

    OnLetterShowListener listener;

    public void setOnLetterChangedListener(OnLetterShowListener l) {
        listener = l;
    }

    public void removeOnLetterChangedListener() {
        listener = null;
    }

    interface OnLetterShowListener {
        void onLetterShow(String letter);

        void onLetterDismiss();
    }
}
