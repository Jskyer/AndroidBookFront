package com.example.newhelloworld.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newhelloworld.manager.MyActivityManager;


public class BaseActivity extends AppCompatActivity {

//    public static void startAction(Context context){
//        Intent intent = new Intent(context, HistoryActivity.class);
//        context.startActivity(intent);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityManager.getInstance().add(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyActivityManager.getInstance().remove(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // toolbar返回键的监听事件，id是系统默认
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}