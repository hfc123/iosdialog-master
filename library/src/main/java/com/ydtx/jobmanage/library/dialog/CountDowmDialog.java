package com.ydtx.jobmanage.library.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ydtx.jobmanage.library.R;
import com.ydtx.jobmanage.library.dialog.baseDialog.CountDownBaseDialog;

public class CountDowmDialog extends CountDownBaseDialog {
    int count = 5;
    public CountDowmDialog(@NonNull Context context) {
        super(context);
    }

    public CountDowmDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CountDowmDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void initView() {
        super.initView();
        setCanceledOnTouchOutside(false);
    }

    public TextView close;
    public TextView content;
    public TextView head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdowndialog);
        close = findViewById(R.id.close);
        content = findViewById(R.id.content);
        head = findViewById(R.id.head);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    @Override
    public void onTick(long millisUntilFinished) {
        count--;
        close.setTextColor(getContext().getResources().getColor(R.color.content));
        close.setEnabled(false);
        close.setText("我知道了 "+count+"秒");
    }

    @Override
    public void onFinish() {
        close.setText("我知道了");
        close.setTextColor(getContext().getResources().getColor(R.color.blueclose));
        close.setEnabled(true);
    }


}
