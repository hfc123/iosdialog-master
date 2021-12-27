package com.ydtx.jobmanage.library.dialog.baseDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ydtx.jobmanage.library.R;
import com.ydtx.jobmanage.library.dialog.CountDowmDialog;
import com.ydtx.jobmanage.library.interfaces.OnDialogCountDownLisener;

public class CountDownBaseDialog extends Dialog implements OnDialogCountDownLisener{
    public static long TOTAL_TIME=5000;
    public static long ONECE_TIME=1000;
    public OnDialogCountDownLisener onDialogCountDownLisener ;
    public CountDownBaseDialog(@NonNull Context context) {
        super(context);
        initView();

    }

    public void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setdefaultAnimation();
    }
    public void setdefaultAnimation(){
        setAnimation(R.style.dialogWindowAnim);
    }
    public void  setAnimation(int style){
        Window window = getWindow(); //
        window.setWindowAnimations(style); //
    }
    public CountDownBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    protected CountDownBaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void show() {
        super.show();
        countDownTimer.start();
    }

    public OnDialogCountDownLisener getOnDialogCountDownLisener() {
        return onDialogCountDownLisener;
    }

    public void setOnDialogCountDownLisener(OnDialogCountDownLisener onDialogCountDownLisener) {
        this.onDialogCountDownLisener = onDialogCountDownLisener;
    }

    private CountDownTimer countDownTimer = new CountDownTimer(TOTAL_TIME, ONECE_TIME) {
        @Override
        public void onTick(long millisUntilFinished) {
            CountDownBaseDialog.this.onTick(millisUntilFinished);
            if (onDialogCountDownLisener!=null)
            onDialogCountDownLisener.onTick(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            CountDownBaseDialog.this.onFinish();
            if (onDialogCountDownLisener!=null)
            onDialogCountDownLisener.onFinish();
        }
    };

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        setWidth(0.8f);
        // setOnItemClickLisener();
    }


    private void setWidth(float ratio) {
        Window window = getWindow();
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (displayMetrics.widthPixels * ratio);
        window.setAttributes(attributes);
    }

}
