package com.ydtx.jobmanage.libs;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ydtx.jobmanage.library.dialog.CloseDialog;
import com.ydtx.jobmanage.library.dialog.CountDowmDialog;
import com.ydtx.jobmanage.library.dialog.CustomDialog;
import com.ydtx.jobmanage.library.dialog.ListViewDialog;
import com.ydtx.jobmanage.library.dialog.baseDialog.CountDownBaseDialog;
import com.ydtx.jobmanage.library.interfaces.OnDialogCountDownLisener;
import com.ydtx.jobmanage.library.loading.LoadingDailog;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @butterknife.BindView(R.id.button1)
    Button button1;
    @butterknife.BindView(R.id.button2)
    Button button2;
    @butterknife.BindView(R.id.button3)
    Button button3;
    @butterknife.BindView(R.id.button4)
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);
    }

    @butterknife.OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                final CustomDialog dialog=new CustomDialog(MainActivity.this,R.style.customDialog,R.layout.ioslayout2);
                dialog.show();
                TextView tvCancel = (TextView) dialog.findViewById(R.id.cancel);
                TextView tvOk = (TextView) dialog.findViewById(R.id.ok);
                tvOk.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        //submitdata();
                    }
                });
                tvCancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.button2:
                final CloseDialog  dialog1 = new CloseDialog(MainActivity.this);
                dialog1.show();
                dialog1.setOnCloselisenner(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                break;
            case R.id.button3:
                final  List<String> data= Arrays.asList("item1","item2","item3","item4","item5","item6","item7","item8","item9","item10");
               final   ListViewDialog  dialog2 = new ListViewDialog(MainActivity.this,data);
                dialog2.setdefaultAnimation();
                dialog2.show();
                dialog2.setOnItemClickLisener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dialog2.dismiss();
                        Toast.makeText(MainActivity.this,data.get(position),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.button4:
                LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(this)
                        .setMessage("加载中...")
                        .setCancelable(true)
                        .setCancelOutside(true);
                final LoadingDailog dialog4=loadBuilder.create();
                dialog4.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog4.dismiss();
                    }
                },2000);
                break;
            case R.id.button5:
               showcountDownDialog();
                break;
        }
    }

    private void showcountDownDialog() {
        CountDowmDialog countDowmDialog = new CountDowmDialog(this);
        countDowmDialog.setOnDialogCountDownLisener(new OnDialogCountDownLisener() {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        });
        countDowmDialog.show();

    }
}
