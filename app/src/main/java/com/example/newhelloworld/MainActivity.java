package com.example.newhelloworld;

import static android.opengl.ETC1.getHeight;
import static android.opengl.ETC1.getWidth;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.activity.SettingActivity;
import com.example.newhelloworld.adapter.EpisodeAdapter;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.views.AudioRoundProgressView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * 底部logo图标，点击之后弹出当前播放歌曲详情页
     */
    private ShapeableImageView ivLogo;
    /**
     * 底部当前播放歌名
     */
    private MaterialTextView tvSongName;
    /**
     * 底部当前歌曲控制按钮, 播放和暂停
     */
    private MaterialButton btnPlay;
    /**
     * 音频播放器
     */
    private MediaPlayer mediaPlayer;
    /**
     * 记录当前播放歌曲的位置
     */
    public int mCurrentPosition = -1;

    /**
     * 自定义进度条
     */
    private AudioRoundProgressView musicProgress;

    /**
     * 音乐进度间隔时间
     */
    private static final int INTERNAL_TIME = 1000;

    /**
     * 图片动画
     */
    private ObjectAnimator logoAnimation;

    BottomNavigationView bottomNavigation;

    private SharedPreferences pref;

    // main布局底部bar
    private View layBottom;
    // bottomSeet 行为设置
    private BottomSheetBehavior mBottomSheetBehavior;
    // 播放列表全局layout
    private View episodeListView;
    // 播放列表的可拖拽列表部分
    private View sheetView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("mode_pref", MODE_PRIVATE);
        boolean isSwitchOn = pref.getBoolean("isSwitchOn", false);
        if(isSwitchOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }



        setEpisodeListSheet();

        //点击弹出播放列表
        layBottom = findViewById(R.id.lay_bottom);
        layBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if(mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }
        });



        //导航
        bottomNavigation = findViewById(R.id.bottom_navigation);
        //获取navController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //通过setupWithNavController将底部导航和导航控制器进行绑定
        NavigationUI.setupWithNavController(bottomNavigation,navController);
    }

    public void setEpisodeListSheet(){
        //获取播放列表视图
        episodeListView = findViewById(R.id.bottom_sheet);
//        episodeListView = getLayoutInflater().inflate(R.layout.episode_list_activity, null);
        sheetView = episodeListView.findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(sheetView);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        //获取数据
        List<Episode> episodeList = new ArrayList<>();
        for (int i = 0; i < 15; i++){
            Episode item = new Episode("episode item","aa", i, LocalDateTime.now());
            episodeList.add(item);
        }

        //设置视图内列表
        RecyclerView rcycView = episodeListView.findViewById(R.id.episode_list);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        rcycView.setLayoutManager(manager);

        EpisodeAdapter adapter = new EpisodeAdapter(episodeList);
        rcycView.setAdapter(adapter);

    }

}