package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newhelloworld.R;
import com.example.newhelloworld.util.WaterfallUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SubscribeActicity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_acticity);
        WaterfallUtil WaterfallUtil = (WaterfallUtil) findViewById(R.id.waterfall);
        WaterfallUtil.setup();

        FloatingActionButton fab=findViewById(R.id.back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });


    }

}

