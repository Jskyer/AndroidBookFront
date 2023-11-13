package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.adapter.EpisodeAdapter;
import com.example.newhelloworld.model.Episode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EpisodeListActivity extends AppCompatActivity {
    private List<Episode> episodeList;

    public static void startAction(Context context){
        Intent intent = new Intent(context, EpisodeListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_list_activity);

        initData();
        RecyclerView rcycView = findViewById(R.id.episode_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);

        EpisodeAdapter adapter = new EpisodeAdapter(episodeList);
        rcycView.setAdapter(adapter);
    }

    public void initData(){
        episodeList = new ArrayList<>();
        for (int i = 1; i < 12; i++){
            Episode item = new Episode("episode item","aa", i, LocalDateTime.now());
            episodeList.add(item);
        }
    }


}
