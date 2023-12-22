package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.newhelloworld.LoginActivity;
import com.example.newhelloworld.MainActivity;
import com.example.newhelloworld.R;
import com.example.newhelloworld.databinding.SettingLayoutBinding;
import com.example.newhelloworld.manager.MyActivityManager;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.signIn.ResetPassResp;
import com.example.newhelloworld.util.PreferenceUtil;

import org.apache.commons.lang3.StringUtils;

public class SettingActivity extends ViewBindingActivity<SettingLayoutBinding> implements View.OnClickListener{
    private final String TAG = "SettingActivity";
    private AlertDialog alertDialog;
    private LinearLayout modifyPwdLayout;

    private MyRetrofitClient client;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.setting_layout);

        client = new MyRetrofitClient();
        modifyPwdLayout = binding.modifyPwd;
        modifyPwdLayout.setOnClickListener(this);

        binding.btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.modify_pwd){
            showDialog();
        }else if (id == R.id.btn_logout){
            logout();
        }

    }

    public void logout(){
//        PreferenceUtil.removeString(this, PreferenceUtil.KEY_USER_TOKEN);
//        PreferenceUtil.removeString(this, PreferenceUtil.K);
        PreferenceUtil.clearPreferences(this);
        MyActivityManager.getInstance().finishAll();
        LoginActivity.startAction(this);
    }


    private void showDialog(){
        Log.d(TAG, "showDialog");
        // 创建对话框布局视图
        View view = getLayoutInflater().inflate(R.layout.dialog_modifypwd, null);
        EditText txtView = view.findViewById(R.id.pwdEdit);
        // 创建AlertDialog对象
        alertDialog = new AlertDialog.Builder(SettingActivity.this)
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "open");

                        String s = txtView.getText().toString();
                        if(StringUtils.isEmpty(s)){
                            Toast.makeText(SettingActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        modifyPwd(s);
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

    public void modifyPwd(String s){
        client.resetPwd(s, new MyObserver<ResetPassResp>() {
            @Override
            public void onSuccss(ResetPassResp resp) {
                Status status = resp.getStatus();
                if (status.getCode() != 200){
                    Log.d(TAG, status.getMsg());
                    Toast.makeText(SettingActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(SettingActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public static void startAction(Context context){
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }



}
