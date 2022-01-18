package com.ydtx.jobmanage.library.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import com.ydtx.jobmanage.library.R;

public class RabbitLoading extends Dialog {
    AnimationDrawable animationDrawable;

    ImageView imageView_1;
    public RabbitLoading(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public RabbitLoading(@NonNull Context context) {
        this(context, R.style.TransparentDialog);
        setContentView(R.layout.loading_dialog);
        imageView_1 = findViewById(R.id.image);
        setanimation(R.drawable.loading);
        setCancelable(false);
    }

    private void setanimation(@DrawableRes int loading) {

        imageView_1.setImageResource(loading);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);


    }

    @Override
    public void show() {
        super.show();
        startanimation();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        stopAnimation();
    }

    public void  startanimation(){
        animationDrawable = (AnimationDrawable) imageView_1.getDrawable();
        if (animationDrawable!=null)
        animationDrawable.start();
    }

    public void stopAnimation(){
        if (animationDrawable!=null)
            animationDrawable.stop();
    }
}
