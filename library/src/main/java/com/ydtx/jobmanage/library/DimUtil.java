package com.ydtx.jobmanage.library;

import android.content.res.Resources;


/**
 * @author hongfuchang
 * @description:px 和dip之间转换方法，使用dp ，如果是px可直接传,代码接收的是Pixel 需抓换成px
 * @email 284424243@qq.com
 * @date :2022/1/13 10:04
 **/
public class DimUtil {

    public static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int pxToDip(int px){
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    public static float dipToPx(float dip){
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return  (dip * scale + 0.5f);
    }


    public static int pxToSp(float px) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5f);
    }

    public static int spToPx( float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}

