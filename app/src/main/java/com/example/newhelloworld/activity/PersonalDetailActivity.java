package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.newhelloworld.R;
import com.example.newhelloworld.databinding.PersonalDetailLayoutBinding;

public class PersonalDetailActivity extends ViewBindingActivity<PersonalDetailLayoutBinding> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static void startAction(Context context){
        Intent intent = new Intent(context, PersonalDetailActivity.class);
        context.startActivity(intent);
    }
}
