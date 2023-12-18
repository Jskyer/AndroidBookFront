package com.example.newhelloworld.activity;

import android.os.Bundle;

import com.example.newhelloworld.adapter.AlbumAdapter;
import com.example.newhelloworld.event.MsgToCategory;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.Album;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.album.GetPopularAlbumResp;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.example.newhelloworld.R;
import com.example.newhelloworld.databinding.ActivityCategoryBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
//TODO:ALBUM后端修改后测试

public class CategoryActivity extends AppCompatActivity {
    public static final String TAG = "CategoryActivity";
    private List<Album> albumPodcastEpisodeList;
    private ImageButton btn_back;
    private ActivityCategoryBinding binding;

    private RecyclerView rcycView;

    private MyRetrofitClient client;
    private Album album;


    private int pageNum = 1;
    private int pageSize = 6;
    private String type = "";

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onCategoryReceived(MsgToCategory msg){
        Log.d(TAG, "onCategoryReceived");

        type = msg.getType();
        getAlbumByType();

        EventBus.getDefault().removeStickyEvent(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(type);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;




        client = new MyRetrofitClient();

//        initData();

        rcycView = findViewById(R.id.album_episode_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);

        albumPodcastEpisodeList = new ArrayList<>();
//        PodcastEpisodeAdapter adapter = new PodcastEpisodeAdapter(albumPodcastEpisodeList,this);
//        rcycView.setAdapter(adapter);

        btn_back=(ImageButton)findViewById(R.id.btn_category_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

//    public void initData(){
//        albumPodcastEpisodeList = new ArrayList<>();
//        for (int i = 1; i < 20; i++){
//            PodcastEpisode item = new PodcastEpisode("title",
//                    "description",
//                    i, LocalDateTime.now());
//            albumPodcastEpisodeList.add(item);
//        }
//    }

    public void getAlbumByType(){
        client.getAlbumByType(pageNum, pageSize, type, new MyObserver<GetPopularAlbumResp>() {
            @Override
            public void onSuccss(GetPopularAlbumResp getPopularAlbumResp) {
                Status status = getPopularAlbumResp.getStatus();
                if(status.getCode() == 200){
                    albumPodcastEpisodeList = getPopularAlbumResp.getAlbums();

                    AlbumAdapter adapter = new AlbumAdapter(albumPodcastEpisodeList, CategoryActivity.this);
                    rcycView.setAdapter(adapter);

                    Log.d(TAG, "getAlbumByType ok");
                }else{
                    Log.d(TAG, "getAlbumByType error");
                }

            }
        });
    }


}