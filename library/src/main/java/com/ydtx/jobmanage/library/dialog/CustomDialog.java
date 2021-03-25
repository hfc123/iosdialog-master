package com.ydtx.jobmanage.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.ydtx.jobmanage.library.R;

public class CustomDialog extends Dialog {
    private Context context;
    private int resId;
    public CustomDialog(Context context, int resLayout) {
        this(context,0,0);
    }
    public CustomDialog(Context context, int themeResId, int resLayout) {
        super(context, themeResId);
        this.context = context;
        this.resId = resLayout;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(resId);
    }
    public void setdefaultAnimation(){
        setAnimation(R.style.dialogWindowAnim);
    }
    public void  setAnimation(int style){
        Window window = getWindow(); //
        window.setWindowAnimations(style); //
    }
}
