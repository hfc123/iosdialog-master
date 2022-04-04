package com.ydtx.jobmanage.library.permessiondialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ydtx.jobmanage.library.R;
import com.ydtx.jobmanage.library.dialog.baseDialog.RoundBaseDialog;

import java.security.acl.Permission;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

/**
 * @author hongfuchang
 * @description:
 * 权限申请说明（Permission application description）
 * 申请权限前需要先显示说明弹窗，保证用户隐私权限
 * @email 284424243@qq.com
 * @date :2022/4/4 15:32
 **/
public class PDADialog extends RoundBaseDialog {
    private static  String[] PERMISSION_GETPERMISSION=new String[]{READ_EXTERNAL_STORAGE};
    View reject;
    View allow;
    private OnDialogButtonClickLisener onDialogButtonClickLisener ;

    public PDADialog(@NonNull Context context) {
        super(context);
    }

    public PDADialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected PDADialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.pdalayout);
        allow= findViewById(R.id.allow);
        reject=findViewById(R.id.reject);
        allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Toast.makeText(getContext(),"allow",Toast.LENGTH_SHORT).show();
                if (onDialogButtonClickLisener!=null){
                    onDialogButtonClickLisener.onAllowLisener();
                }
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Toast.makeText(getContext(),"reject",Toast.LENGTH_SHORT).show();
                if (onDialogButtonClickLisener!=null){
                    onDialogButtonClickLisener.onRejectLisener();
                }
            }
        });
    }

    interface OnDialogButtonClickLisener{
        void onRejectLisener();
        void onAllowLisener();
    }

    public OnDialogButtonClickLisener getOnDialogButtonClickLisener() {
        return onDialogButtonClickLisener;
    }

    public static String[] getPermissionGetpermission() {
        return PERMISSION_GETPERMISSION;
    }

    public static void setPermissionGetpermission(String[] permissionGetpermission) {
        PERMISSION_GETPERMISSION = permissionGetpermission;
    }

    public void setOnDialogButtonClickLisener(OnDialogButtonClickLisener onDialogButtonClickLisener) {
        this.onDialogButtonClickLisener = onDialogButtonClickLisener;
    }
}
