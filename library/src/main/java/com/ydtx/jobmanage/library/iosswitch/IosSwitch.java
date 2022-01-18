package com.ydtx.jobmanage.library.iosswitch;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.ydtx.jobmanage.library.R;
import com.ydtx.jobmanage.library.dialog.GestureUtils;

/**
 * Created by caihan on 2017/2/10.
 *
 */
public class IosSwitch extends View implements View.OnClickListener {

    private static final String TAG = "IosSwitch";

    private final int BORDER_WIDTH = 2;

    private int mBasePlaneColor ;//
    private int mOpenSlotColor ;//
    private int mOffSlotColor ;//
    private int roundcolor;

    public int getmBasePlaneColor() {
        return mBasePlaneColor;
    }

    public void setmBasePlaneColor(int mBasePlaneColor) {
        this.mBasePlaneColor = mBasePlaneColor;
        invalidate();
    }

    public int getmOpenSlotColor() {
        return mOpenSlotColor;
    }

    public void setmOpenSlotColor(int mOpenSlotColor) {
        this.mOpenSlotColor = mOpenSlotColor;
        invalidate();
    }

    public int getmOffSlotColor() {
        return mOffSlotColor;
    }

    public void setmOffSlotColor(int mOffSlotColor) {
        this.mOffSlotColor = mOffSlotColor;
        invalidate();
    }

    public int getRoundcolor() {
        return roundcolor;
    }

    public void setRoundcolor(int roundcolor) {
        this.roundcolor = roundcolor;
        invalidate();
    }

    private int mSlotColor;

    private RectF mRect = new RectF();

    //
    private float mBackPlaneRadius;//
    private float mSpotRadius;//

    private float spotStartX;//
    private float mSpotY;//
    private float mOffSpotX;//

    private Paint mPaint;//

    private boolean mIsToggleOn = false;//
    private boolean isTouchEvent = false;//
    private boolean isMoveing = false;//

    private OnToggleListener mOnToggleListener;//toggle

    private GestureUtils mGestureUtils;//

    public interface OnToggleListener {
        void onSwitchChangeListener(boolean switchState);
    }

    public IosSwitch(Context context) {
        this(context,null);

    }

    public IosSwitch(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public IosSwitch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //Retrieve styles attributes
        Resources res = context.getResources();
        init(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IosSwitchStyleable,R.attr.sIosSwitchDefaultStyle,  R.style.IosSwitchDefaultStyle);
        mBasePlaneColor=a.getColor(R.styleable.IosSwitchStyleable_mBasePlaneColor,res.getColor(R.color.white));//
        mOpenSlotColor =a.getColor(R.styleable.IosSwitchStyleable_mOpenSlotColor,res.getColor(R.color.white));
        mOffSlotColor  =a.getColor(R.styleable.IosSwitchStyleable_mOffSlotColor,res.getColor(R.color.white));
        roundcolor     =a.getColor(R.styleable.IosSwitchStyleable_roundcolor,res.getColor(R.color.white));
    }


    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setOnClickListener(this);
        setEnabled(true);
        mGestureUtils = new GestureUtils();
    }

    @Override
    public void onClick(View v) {
        onClickToggle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mGestureUtils.actionDown(event);
                isTouchEvent = false;
                isMoveing = false;
                break;
            case MotionEvent.ACTION_MOVE:
                mGestureUtils.actionMove(event);
                if (mGestureUtils.getGesture(GestureUtils.Gesture.PullLeft)) {
                    //
                    isTouchEvent = true;
                    touchToggle(false);
                    return true;
                } else if (mGestureUtils.getGesture(GestureUtils.Gesture.PullRight)) {
                    //
                    isTouchEvent = true;
                    touchToggle(true);
                    return true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                isMoveing = false;
                if (isTouchEvent) {
                    //
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int resultWidth = wSize;
        int resultHeight = hSize;
        Resources r = Resources.getSystem();
        //lp = wrapcontent
        if (wMode == MeasureSpec.AT_MOST) {
            resultWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        }
        if (hMode == MeasureSpec.AT_MOST) {
            resultHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics());
        }
        setMeasuredDimension(resultWidth, resultHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mBackPlaneRadius = Math.min(getWidth(), getHeight()) * 0.5f;
        mSpotRadius = mBackPlaneRadius - BORDER_WIDTH;
        spotStartX = 0;
        mSpotY = 0;
        mOffSpotX = getMeasuredWidth() - mBackPlaneRadius * 2;
        mSlotColor = mOffSlotColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //
        mRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        mPaint.setColor(mBasePlaneColor);
        canvas.drawRoundRect(mRect, mBackPlaneRadius, mBackPlaneRadius, mPaint);

        //
        mRect.set(BORDER_WIDTH,
                BORDER_WIDTH,
                getMeasuredWidth() - BORDER_WIDTH,
                getMeasuredHeight() - BORDER_WIDTH);

        mPaint.setColor(mSlotColor);
        canvas.drawRoundRect(mRect, mSpotRadius, mSpotRadius, mPaint);


        mRect.set(spotStartX,
                mSpotY,
                spotStartX + mBackPlaneRadius * 2,
                mSpotY + mBackPlaneRadius * 2);

        mPaint.setColor(mBasePlaneColor);
        canvas.drawRoundRect(mRect, mBackPlaneRadius, mBackPlaneRadius, mPaint);

        //
        mRect.set(spotStartX + BORDER_WIDTH,
                mSpotY + BORDER_WIDTH,
                mSpotRadius * 2 + spotStartX + BORDER_WIDTH,
                mSpotRadius * 2 + mSpotY + BORDER_WIDTH);

        mPaint.setColor(roundcolor);
        canvas.drawRoundRect(mRect, mSpotRadius, mSpotRadius, mPaint);
    }

    //
    public void setRoundColor(int color){
        roundcolor=color;
    }
    //
    public void setmBackgroudColor(int coloropen,int colorclose){
        mOpenSlotColor = coloropen;//
       mOffSlotColor = colorclose;//
    }
    public void setLineColor(int color){
        mBasePlaneColor =color ;// e.g : Color.parseColor("#4ebb7f")
    }


    public float getSpotStartX() {
        return spotStartX;
    }

    public void setSpotStartX(float spotStartX) {
        this.spotStartX = spotStartX;
    }


    public void calculateColor(float fraction, int startColor, int endColor) {
        final int fb = Color.blue(startColor);
        final int fr = Color.red(startColor);
        final int fg = Color.green(startColor);

        final int tb = Color.blue(endColor);
        final int tr = Color.red(endColor);
        final int tg = Color.green(endColor);

        //
        int sr = (int) (fr + fraction * (tr - fr));
        int sg = (int) (fg + fraction * (tg - fg));
        int sb = (int) (fb + fraction * (tb - fb));
        //
        sb = clamp(sb, 0, 255);
        sr = clamp(sr, 0, 255);
        sg = clamp(sg, 0, 255);

        mSlotColor = Color.rgb(sr, sg, sb);
    }

    private int clamp(int value, int low, int high) {
        return Math.min(Math.max(value, low), high);
    }


    public void toggleOn() {
        //
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "spotStartX", 0, mOffSpotX);
        animator.setDuration(300);
        animator.start();
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                calculateColor(fraction, mOffSlotColor, mOpenSlotColor);
                invalidate();
            }
        });
    }


    public void toggleOff() {
        //
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "spotStartX", mOffSpotX, 0);
        animator.setDuration(300);
        animator.start();
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                calculateColor(fraction, mOpenSlotColor, mOffSlotColor);
                invalidate();
            }
        });
    }

    public boolean getSwitchState() {
        return mIsToggleOn;
    }

    private void touchToggle(boolean open) {
        if (!isMoveing) {
            isMoveing = true;
            if (mIsToggleOn != open) {
                if (mIsToggleOn) {
                    toggleOff();
                } else {
                    toggleOn();
                }
                mIsToggleOn = !mIsToggleOn;
                if (mOnToggleListener != null) {
                    mOnToggleListener.onSwitchChangeListener(mIsToggleOn);
                }
            }
        }
    }


    private void onClickToggle() {
        if (mIsToggleOn) {
            toggleOff();
        } else {
            toggleOn();
        }
        mIsToggleOn = !mIsToggleOn;
        if (mOnToggleListener != null) {
            mOnToggleListener.onSwitchChangeListener(mIsToggleOn);
        }
    }

    public void setOnToggleListener(OnToggleListener listener) {
        mOnToggleListener = listener;
    }

    public void setChecked(final boolean open) {
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                touchToggle(open);
            }
        }, 300);
    }
}


