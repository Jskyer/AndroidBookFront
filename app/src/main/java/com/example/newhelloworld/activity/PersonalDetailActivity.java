package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newhelloworld.R;

public class PersonalDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_detail_layout);
    }

    public static void startAction(Context context){
        Intent intent = new Intent(context, PersonalDetailActivity.class);
        context.startActivity(intent);
    }
}
