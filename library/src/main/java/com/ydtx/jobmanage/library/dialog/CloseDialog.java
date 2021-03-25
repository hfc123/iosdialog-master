package com.ydtx.jobmanage.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ydtx.jobmanage.library.R;

public class CloseDialog extends Dialog {

    private final Context mContext;
    private TextView close;


    public CloseDialog(Context context) {
        super(context);

        mContext = context;
        initView();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCanceledOnTouchOutside(false);

    }

    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View contentView = View.inflate(mContext, R.layout.content_dialog2, null);
        close = (TextView) contentView.findViewById(R.id.close);
        setContentView(contentView);
    }

    public void setdefaultAnimation(){
        setAnimation(R.style.dialogWindowAnim);
    }
    public void  setAnimation(int style){
        Window window = getWindow(); //
        window.setWindowAnimations(style); //
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        setHeight();
        // setOnItemClickLisener();
    }

    public void setOnCloselisenner(View.OnClickListener closelisenner){
        close.setOnClickListener(closelisenner);
    }
    private void setHeight() {
        Window window = getWindow();
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (displayMetrics.widthPixels * 0.8);
        if (window.getDecorView().getHeight() >= (int) (displayMetrics.heightPixels * 0.6)) {
            attributes.height = (int) (displayMetrics.heightPixels * 0.6);

        }
        window.setAttributes(attributes);
    }


}