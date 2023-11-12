package com.example.newhelloworld.activity;

import android.media.Image;
import android.os.Bundle;

import com.example.newhelloworld.adapter.PodcastEpisodeAdapter;
import com.example.newhelloworld.model.PodcastEpisode;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;

import com.example.newhelloworld.R;
import com.example.newhelloworld.databinding.ActivityCategoryBinding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private List<PodcastEpisode> albumPodcastEpisodeList;
    private ImageButton btn_back;
private ActivityCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityCategoryBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initData();
        RecyclerView rcycView = findViewById(R.id.album_episode_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);
        PodcastEpisodeAdapter adapter = new PodcastEpisodeAdapter(albumPodcastEpisodeList);
        rcycView.setAdapter(adapter);
        btn_back=(ImageButton)findViewById(R.id.btn_category_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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


}