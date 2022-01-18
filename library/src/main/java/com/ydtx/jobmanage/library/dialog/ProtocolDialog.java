package com.ydtx.jobmanage.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.ydtx.jobmanage.library.R;

public class ProtocolDialog extends Dialog implements View.OnClickListener {
    public static final String PrivacyPolicy="PrivacyPolicy";
    public static final String UserPolicy="UserPolicy";
    private Callback callback;
    private boolean needDismiss = true;

    public boolean isNeedDismiss() {
        return needDismiss;
    }

    public void setNeedDismiss(boolean needDismiss) {
        this.needDismiss = needDismiss;
    }

    public ProtocolDialog(@NonNull Context context, Callback callback) {
        super(context, R.style.customDialog);
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_protocol);
        initTextClickableSpan();
    }
    //YOU CAN OVERRIDE THIS METHOD TO DO SOMETHING
    public void initTextClickableSpan(){
        View view1 = findViewById(R.id.protocol_ok);
        if (view1 != null) {
            view1.setOnClickListener(this);
        }
        View view2 = findViewById(R.id.protocol_cancel);
        if (view2 != null) {
            view2.setOnClickListener(this);
        }
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        TextView textView = findViewById(R.id.protocol_content);
        String tag1 = textView.getResources().getString(R.string.park_user_protocol);
        String tag2 = textView.getResources().getString(R.string.park_privacy_policy);
        String content = "请您务必审慎阅读、充分理解“用户协议”和“隐私政策”各条款，我们需要收集您的设备信息、操作日志等个人信息。您可以在“我的”中查看、变更、删除个人信息。\n" +
                "您可阅读" + tag1 + "和" + tag2 + "了解详细信息。如您同意，请点击“同意”开始接受我们的服务。";

        int index1 = content.indexOf(tag1);
        int index2 = content.indexOf(tag2);
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        builder.setSpan(new TextClickableSpan(PrivacyPolicy), index1, index1 + tag1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new TextClickableSpan(UserPolicy), index2, index2 + tag2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }


    private class TextClickableSpan extends ClickableSpan {

        private String flag;

        private TextClickableSpan(String flag) {
            this.flag = flag;
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            //super.updateDrawState(ds);
            ds.setColor(getContext().getResources().getColor(R.color.mBasePlaneColor));
        }

        @Override
        public void onClick(@NonNull View widget) {
            if (ProtocolDialog.this.callback != null) {
                ProtocolDialog.this.callback.onClick(this.flag);
            }
        }
    }

    public void destroy() {
        if (isShowing()) {
            dismiss();
        }
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window != null) {
            WindowManager windowManager = window.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = (int) (display.getWidth() * 0.8f); //设置宽度
            window.setAttributes(lp);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.protocol_ok) {
            if (this.callback != null) {
                this.callback.onCall(true);
            }
        } else if (id == R.id.protocol_cancel) {
            if (this.callback != null) {
                this.callback.onCall(false);
            }

        }
        if (needDismiss){
            dismiss();
        }

    }

    public interface Callback {
        void onCall(boolean accept);
        void onClick(String flag);
    }
}
