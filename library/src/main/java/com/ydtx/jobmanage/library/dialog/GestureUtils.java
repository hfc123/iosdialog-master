package com.ydtx.jobmanage.library.dialog;

import android.view.MotionEvent;

/**
 * Created by caihan on 2017/2/10.
 *
 */
public class GestureUtils {
    private static final String TAG = "GestureUtils";

    private float startX = 0f;
    private float endX = 0f;
    private float startY = 0f;
    private float endY = 0f;
    private float xDistance = 0f;
    private float yDistance = 0f;

    public enum Gesture {
        PullUp, PullDown, PullLeft, PullRight
    }

    public GestureUtils() {

    }


    public void actionDown(MotionEvent event) {
        xDistance = yDistance = 0f;
        setStartX(event);
        setStartY(event);
    }

    public void actionMove(MotionEvent event) {
        setEndX(event);
        setEndY(event);
    }


    public void actionUp(MotionEvent event) {
        setEndX(event);
        setEndY(event);
    }

    public boolean getGesture(Gesture gesture) {
        switch (gesture) {
            case PullUp:
                return isRealPullUp();
            case PullDown:
                return isRealPullDown();
            case PullLeft:
                return isRealPullLeft();
            case PullRight:
                return isRealPullRight();
            default:
              //  LogUtils.e(TAG, "getGesture error");
                return false;
        }
    }


    private float gestureRawX(MotionEvent event) {
        return event.getRawX();
    }

    private float gestureRawY(MotionEvent event) {
        return event.getRawY();
    }


    private float gestureDistanceX(float startX, float endX) {
        setxDistance(Math.abs(endX - startX));
        return xDistance;
    }


    private float gestureDistanceY(float startY, float endY) {
        setyDistance(Math.abs(endY - startY));
        return yDistance;
    }


    private boolean isPullUp(float startY, float endY) {
        return (endY - startY) < 0;
    }


    private boolean isPullDown(float startY, float endY) {
        return (endY - startY) > 0;
    }

    private boolean isPullRight(float startX, float endX) {
        return (endX - startX) > 0;
    }


    private boolean isPullLeft(float startX, float endX) {
        return (endX - startX) < 0;
    }


    private boolean isRealPullUp() {
        if (gestureDistanceX(startX, endX) < gestureDistanceY(startY, endY)) {
            return isPullUp(startY, endY);
        }
        return false;
    }


    private boolean isRealPullDown() {
        if (gestureDistanceX(startX, endX) < gestureDistanceY(startY, endY)) {
            return isPullDown(startY, endY);
        }
        return false;
    }

    private boolean isRealPullLeft() {
        if (gestureDistanceX(startX, endX) > gestureDistanceY(startY, endY)) {
            return isPullLeft(startX, endX);
        }
        return false;
    }

    private boolean isRealPullRight() {
        if (gestureDistanceX(startX, endX) > gestureDistanceY(startY, endY)) {
            return isPullRight(startX, endX);
        }
        return false;
    }


    private GestureUtils setStartX(MotionEvent event) {
        this.startX = gestureRawX(event);
        return this;
    }

    private GestureUtils setEndX(MotionEvent event) {
        this.endX = gestureRawX(event);
        return this;
    }

    private GestureUtils setStartY(MotionEvent event) {
        this.startY = gestureRawY(event);
        return this;
    }

    private GestureUtils setEndY(MotionEvent event) {
        this.endY = gestureRawY(event);
        return this;
    }

    private GestureUtils setxDistance(float xDistance) {
        this.xDistance = xDistance;
        return this;
    }

    private GestureUtils setyDistance(float yDistance) {
        this.yDistance = yDistance;
        return this;
    }

    public float getStartX() {
        return startX;
    }

    public float getEndX() {
        return endX;
    }

    public float getStartY() {
        return startY;
    }

    public float getEndY() {
        return endY;
    }

    public float getxDistance() {
        return xDistance;
    }

    public float getyDistance() {
        return yDistance;
    }
}

