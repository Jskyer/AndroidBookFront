package com.example.newhelloworld.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.newhelloworld.R;


/**
 * 自定义switch
 */
public class SwitchButtonView extends View {
    // 开关状态
    private boolean isSwitchOn;

    private RectF rectF;

    private int width;

    private int height;

    private float radius;

    private float radiusMargin = 8f;

    private Context context;

    private AttributeSet attrSet;

    private int switchOnColor;

    private int switchOffColor;

    private Paint mPaint;

    private SharedPreferences pref;

    public SwitchButtonView(Context context) {
        super(context);
    }

    public SwitchButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        attrSet = attrs;

//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView);
//        mColor = typedArray.getColor(R.styleable.MyCustomView_my_custom_color, Color.BLACK);
//        mSize = (int) typedArray.getDimension(R.styleable.MyCustomView_my_custom_size, 0);

        switchOnColor = context.getColor(R.color.primary);
        switchOffColor = context.getColor(R.color.grey88);

        // 获取保存的外观模式
        pref = context.getSharedPreferences("mode_pref", Context.MODE_PRIVATE);
        isSwitchOn = pref.getBoolean("isSwitchOn", false);

        rectF = new RectF();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(switchOffColor);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

//    private void init(Context context, AttributeSet attrs) {
//        if (attrs != null) {
//            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchButtonView);
//            typedArray.getColor(R.styleable.SwitchButtonView_switch_on_color, R.color.primary)
//            mColor = typedArray.getColor(R.styleable.MyCustomView_my_custom_color, Color.BLACK);
//            mSize = (int) typedArray.getDimension(R.styleable.MyCustomView_my_custom_size, 0);
//            typedArray.recycle();
//        }
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = getWidth();
        height = getHeight();
        radius = Math.min(getWidth(), getHeight()) / 2f;
        rectF.set(0f, 0f, width, height);

        Log.d("night mode", "cur: "+isSwitchOn);

        if (isSwitchOn) {
            mPaint.setColor(switchOnColor);
            canvas.drawRoundRect(rectF, radius, radius, mPaint);
            mPaint.setColor(Color.WHITE);
            canvas.drawCircle(width - radius, radius, radius - radiusMargin, mPaint);
        } else {
            mPaint.setColor(switchOffColor);
            canvas.drawRoundRect(rectF, radius, radius, mPaint);
            mPaint.setColor(Color.WHITE);
            canvas.drawCircle(radius, radius, radius - radiusMargin, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            isSwitchOn = !isSwitchOn;
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isSwitchOn", isSwitchOn);
            editor.apply();

            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                // 关闭暗黑模式
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                isSwitchOn = false;
            } else {
                // 开启暗黑模式
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                isSwitchOn = true;
            }

            Log.d("night mode", "after touch "+isSwitchOn);

            invalidate();
        }


        return true;
    }

    public void setSwitchOn(){
        if(isSwitchOn)return;
        isSwitchOn = true;
    }

    public void setSwitchOff(){
        if(!isSwitchOn)return;
        isSwitchOn = false;
    }

    public boolean getSwitchStatus(){
        return isSwitchOn;
    }


}