package com.ydtx.jobmanage.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.ydtx.jobmanage.library.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewDialog extends Dialog {

    private final Context mContext;
    private ListView mListView;
    private  List<String> list=new ArrayList<>();
    private ArrayAdapter<String> stringArrayAdapter;

    public ListViewDialog(Context context, List<String> list) {
        super(context);

        mContext = context;
        this.list.addAll(list);
        initView();
        initListView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.setCanceledOnTouchOutside(false);
    }

    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View contentView = View.inflate(mContext, R.layout.content_dialog, null);
        mListView = (ListView) contentView.findViewById(R.id.lv);
        setContentView(contentView);
    }

    private void initListView() {
        stringArrayAdapter = new ArrayAdapter<>(mContext, R.layout.item2,list);

        mListView.setAdapter(stringArrayAdapter);
    }
    public void setList(List<String> list) {
      //  this.list = list;
       this.list.clear();
       this.list.addAll(list);
       stringArrayAdapter.notifyDataSetChanged();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        setHeight();
       // setOnItemClickLisener();
    }

    public void setOnItemClickLisener(AdapterView.OnItemClickListener lisener) {
        mListView.setOnItemClickListener(lisener);
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
    public void setdefaultAnimation(){
        setAnimation(R.style.dialogWindowAnim);
    }
    public void  setAnimation(int style){
        Window window = getWindow(); //
        window.setWindowAnimations(style); //
    }
}
