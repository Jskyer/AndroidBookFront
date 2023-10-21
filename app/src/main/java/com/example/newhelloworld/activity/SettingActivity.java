package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.newhelloworld.databinding.SettingLayoutBinding;

public class SettingActivity extends ViewBindingActivity<SettingLayoutBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.setting_layout);

    }

    public static void startAction(Context context){
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }


}
