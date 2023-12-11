package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.adapter.PodcastEpisodeAdapter;
import com.example.newhelloworld.model.PodcastEpisode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PageActivity extends AppCompatActivity {

    private ImageButton button;
    private ImageButton btn_comment;
    private List<PodcastEpisode> albumPodcastEpisodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        button=(ImageButton) findViewById(R.id.btn_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_comment=(ImageButton) findViewById(R.id.page_comment);
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PageActivity.this, CommentActivity.class);
                startActivity(intent);
            }
        });
        // 创建图像数组和文本数组
        initData();
        RecyclerView rcycView = findViewById(R.id.album_episode_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);
        PodcastEpisodeAdapter adapter = new PodcastEpisodeAdapter(albumPodcastEpisodeList,this);
        rcycView.setAdapter(adapter);
    }
    public void initData(){
        albumPodcastEpisodeList = new ArrayList<>();
        for (int i = 1; i < 20; i++){
            PodcastEpisode item = new PodcastEpisode("title",
                    "description",
                    i, LocalDateTime.now());
            albumPodcastEpisodeList.add(item);
        }
    }
    public static void startAction(Context context){
        Intent intent = new Intent(context, PageActivity.class);
        context.startActivity(intent);
    }
}
