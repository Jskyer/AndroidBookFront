package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.adapter.HistoryAdapter;
import com.example.newhelloworld.databinding.HistoryLayoutBinding;
import com.example.newhelloworld.model.Episode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends ViewBindingActivity<HistoryLayoutBinding> {

    private List<Episode> episodeList;

    public static void startAction(Context context){
        Intent intent = new Intent(context, HistoryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.history_layout);

        initData();
        RecyclerView rcycView = findViewById(R.id.personal_history_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);
        HistoryAdapter adapter = new HistoryAdapter(episodeList);
        rcycView.setAdapter(adapter);
    }

    public void initData(){
        episodeList = new ArrayList<>();
        for (int i = 1; i < 20; i++){
            Episode item = new Episode(i,"啊啊啊啊啊啊",
                    "user111",200,
                    LocalDateTime.now(), null);
            episodeList.add(item);
        }
    }
}
