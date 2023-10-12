package com.example.newhelloworld.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newhelloworld.R;


public class PersonalSpaceActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView btn_history;
    private ImageView btn_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);

        initListeners();
    }

    public void initListeners(){
        btn_history = findViewById(R.id.goto_history);
        btn_history.setOnClickListener(this);

        btn_setting = findViewById(R.id.goto_setting);
        btn_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.goto_history){
            HistoryActivity.startAction(this);
        }else if(id == R.id.goto_setting){
            SettingActivity.startAction(this);
        }
    }
}
