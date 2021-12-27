package com.ydtx.jobmanage.library.dialog.baseDialog;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
/*
* 4圆角dialog
* */
public class RoundBaseDialog extends Dialog {
    public RoundBaseDialog(@NonNull Context context) {
        super(context);
    }

    public RoundBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RoundBaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


}
