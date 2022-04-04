package com.ydtx.jobmanage.library.dialog.baseDialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.ydtx.jobmanage.library.DimUtil;
import com.ydtx.jobmanage.library.R;

/*
* 4圆角dialog
* */
public abstract class RoundBaseDialog extends Dialog {
    public RoundBaseDialog(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView();
        setRadius(4);
    }

    public RoundBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected RoundBaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }
    public abstract void setContentView();
    /**
     * 需要在设置页面之后设置
     * {@link #setContentView(int)}
     */
    public void setRadius(int radius){
        GradientDrawable  gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(DimUtil.dipToPx(radius));
        gradientDrawable.setColor(getContext().getResources().getColor(android.R.color.white));
        // 设置圆角的关键
       getWindow().getDecorView().setBackgroundDrawable(gradientDrawable);
    }

}
