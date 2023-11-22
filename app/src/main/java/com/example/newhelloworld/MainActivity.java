package com.example.newhelloworld;


import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.adapter.EpisodeAdapter;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.views.AudioRoundProgressView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final String TAG = "MainActivity";

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
    // 播放列表数据
    private List<Episode> episodeList;

    //recyclerview上下滑动监听
    private RecyclerView.OnScrollListener loadingMoreListener;
    //recyclerview
    private SwipeRecyclerView rcycView;


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
//        sheetView = episodeListView.findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(episodeListView);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);


        //获取数据
        episodeList = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            Episode item = new Episode("episode item","aa", i, LocalDateTime.now());
            episodeList.add(item);
        }

        Log.d(TAG, "" + episodeList.size());

        //设置视图内列表
        rcycView = episodeListView.findViewById(R.id.episode_list);
        rcycView.setItemViewSwipeEnabled(true); // 侧滑删除，默认关闭。

//        episodeListView.post(new Runnable() {
//            @Override
//            public void run() {
//                int parentHeight = episodeListView.getHeight();
//                int mPaddingBottomValue = parentHeight - mBottomSheetBehavior.getPeekHeight();
//                //计算出mRecycleview需要距离底部的偏移量
//                rcycView.setPadding(0,0,0,mPaddingBottomValue);
//            }
//        });

        //滑动监听
//        loadingMoreListener = new RecyclerView.OnScrollListener() {
//            //到达顶部和底部监听
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                // OnScrollListener.SCROLL_STATE_FLING; //屏幕处于甩动状态
//                // OnScrollListener.SCROLL_STATE_IDLE; //停止滑动状态
//                // OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;// 手指接触状态
//                // 记录当前滑动状态
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) { //当前状态为停止滑动
//                    if (!rcycView.canScrollVertically(1)) { // 到达底部
//                        Toast.makeText(MainActivity.this, "底线", Toast.LENGTH_LONG).show();
//                    } else if (!rcycView.canScrollVertically(-1)) { // 到达顶部
//                        Toast.makeText(MainActivity.this, "到顶了", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//            //上滑下滑监听
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (dy < 0) { // 当前处于上滑状态
//                    Log.d(TAG, "up");
//                } else if (dy > 0) { // 当前处于下滑状态
//                    Log.d(TAG, "down");
//                }
//            }
//        };
//        rcycView.addOnScrollListener(loadingMoreListener);




        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL, false);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        rcycView.setLayoutManager(manager);


        EpisodeAdapter adapter = new EpisodeAdapter(episodeList);
        rcycView.setAdapter(adapter);

        OnItemMoveListener  mItemMoveListener = new OnItemMoveListener() {
            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                return false;
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
                // 此方法在Item在侧滑删除时被调用。
                // 从数据源移除该Item对应的数据，并刷新Adapter。
                int position = srcHolder.getAdapterPosition();
                Log.d(TAG, "remove at " + position);
                episodeList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        };

        rcycView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI


    }

}