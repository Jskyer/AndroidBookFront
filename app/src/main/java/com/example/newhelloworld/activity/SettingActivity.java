package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.newhelloworld.R;
import com.example.newhelloworld.databinding.SettingLayoutBinding;

public class SettingActivity extends ViewBindingActivity<SettingLayoutBinding> implements View.OnClickListener{
    private final String TAG = "SettingActivity";
    private AlertDialog alertDialog;
    private LinearLayout modifyPwdLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.setting_layout);

        modifyPwdLayout = binding.modifyPwd;
        modifyPwdLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        showDialog();
    }

    private void showDialog(){
        Log.d(TAG, "showDialog");
        // 创建对话框布局视图
        View view = getLayoutInflater().inflate(R.layout.dialog_modifypwd, null);
        // 创建AlertDialog对象
        alertDialog = new AlertDialog.Builder(SettingActivity.this)
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "open");

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "close");
                    }
                }).create();


        alertDialog.show();
    }

    public static void startAction(Context context){
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }



}
