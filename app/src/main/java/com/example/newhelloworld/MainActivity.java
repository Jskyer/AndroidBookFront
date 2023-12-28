package com.example.newhelloworld;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newhelloworld.activity.AudioActivity;
import com.example.newhelloworld.activity.RecognizeActivity;
import com.example.newhelloworld.adapter.EpisodeAdapter;
import com.example.newhelloworld.event.AudioCompleteListener;
import com.example.newhelloworld.event.LogoutMsg;
import com.example.newhelloworld.event.MsgAddToAudioList;
import com.example.newhelloworld.event.MsgAudioToMain;
import com.example.newhelloworld.event.MsgPlaylistToMain;
import com.example.newhelloworld.event.MsgRemoveInList;
import com.example.newhelloworld.manager.AudioListManager;
import com.example.newhelloworld.manager.MyActivityManager;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.util.ResourceUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AudioCompleteListener {
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
//    private MediaPlayer mediaPlayer;
    /**
     * 记录当前播放歌曲的位置
     */
//    public int mCurrentPosition = -1;

    /**
     * 自定义进度条
     */
//    private AudioRoundProgressView musicProgress;

    /**
     * 音乐进度间隔时间
     */
//    private static final int INTERNAL_TIME = 1000;

    /**
     * 图片动画
     */
//    private ObjectAnimator logoAnimation;

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
//    private RecyclerView.OnScrollListener loadingMoreListener;

    //recyclerview
    private SwipeRecyclerView rcycView;

    private AudioListManager audioListManager;

    //当前laybottom显示的episode
    private Episode curEpisode;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onLogoutReceived(LogoutMsg msg){
        Log.d(TAG, "onLogoutReceived");

        ivLogo.setImageResource(R.drawable.default_botpic);
        tvSongName.setText("欢迎探索~");

        btnPlay.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_play, null));
        btnPlay.setTag("btn_play");

        episodeList = null;
        curEpisode = null;

        EventBus.getDefault().removeStickyEvent(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onBackToMainFromAudio(MsgAudioToMain msg){
        Log.d(TAG, "onBackToMainFromAudio");
        Log.d(TAG, ""+msg);

        Episode episode = msg.getEpisode();
        boolean flag = msg.isPlaying();

        if (flag && audioListManager.isCurInListPlaying(episode)){
            btnPlay.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_pause, null));
            btnPlay.setTag("btn_pause");
        }else{
            btnPlay.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_play, null));
            btnPlay.setTag("btn_play");
        }

//        TODO
        Glide.with(this)
                .load(ResourceUtil.getPodcastPosterPath(episode.getPoster()))
                .centerCrop()
                .into(ivLogo);
//        ivLogo.setImageResource();
        tvSongName.setText(episode.getTitle());

        curEpisode = episode;

        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        EventBus.getDefault().removeStickyEvent(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void backToMainFromPlaylist(MsgPlaylistToMain msg){
        Log.d(TAG, "backToMainFromPlaylist");
        Log.d(TAG, ""+msg);

        setHiddenPlayList();

        Episode episode = msg.getEpisode();

        boolean flag = msg.isPlaying();
        if (flag && audioListManager.isCurInListPlaying(episode)){
            btnPlay.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_pause, null));
            btnPlay.setTag("btn_pause");
        }else{
            btnPlay.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_play, null));
            btnPlay.setTag("btn_play");
        }

//        TODO
        Glide.with(this)
                .load(ResourceUtil.getPodcastPosterPath(episode.getPoster()))
                .centerCrop()
                .into(ivLogo);
//        ivLogo.setImageResource();
        tvSongName.setText(episode.getTitle());

        curEpisode = episode;

        EventBus.getDefault().removeStickyEvent(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onRemoveInList(MsgRemoveInList msg){
        Log.d(TAG, "onRemoveInList");

        if(curEpisode == null){
            Log.d(TAG, "curEpisode null");
            return;
        }

        if(!audioListManager.judgeInList(curEpisode)){
            List<Episode> list = audioListManager.getList();
            if(list.size() == 0){
                Log.d(TAG, "size == 0");

                ivLogo.setImageResource(R.drawable.default_botpic);
                tvSongName.setText("欢迎探索~");
                btnPlay.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_play, null));
                btnPlay.setTag("btn_play");

                curEpisode = null;
            }else{
                Log.d(TAG, "size > 0");

                Episode tmp = list.get(0);
//                TODO
                Glide.with(this)
                        .load(ResourceUtil.getPodcastPosterPath(tmp.getPoster()))
                        .centerCrop()
                        .into(ivLogo);
//                ivLogo.setImageResource();
                tvSongName.setText(tmp.getTitle());

                if (audioListManager.isPlayManagerPlaying() && audioListManager.isCurInListPlaying(tmp)){
                    btnPlay.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_pause, null));
                    btnPlay.setTag("btn_pause");
                }else{
                    btnPlay.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_play, null));
                    btnPlay.setTag("btn_play");
                }

                curEpisode = tmp;
            }
        }


        EventBus.getDefault().removeStickyEvent(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }*/
        setContentView(R.layout.activity_main);

        MyActivityManager.getInstance().add(this);

        pref = getSharedPreferences("mode_pref", MODE_PRIVATE);
        boolean isSwitchOn = pref.getBoolean("isSwitchOn", false);
        if(isSwitchOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        bindView();
        initPlayer();

        setEpisodeListSheet();

        //点击弹出播放列表
        layBottom = findViewById(R.id.lay_bottom);
        layBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //刷新列表
                setHiddenPlayList();

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

        if(isSwitchOn)bottomNavigation.setBackgroundColor(getResources().getColor(R.color.black));

        //注册事件总线
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void bindView(){
        ivLogo = findViewById(R.id.iv_logo);
        ivLogo.setOnClickListener(this);

        tvSongName = findViewById(R.id.tv_song_name);
        btnPlay = findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(this);


        episodeListView = findViewById(R.id.bottom_sheet);
        rcycView = episodeListView.findViewById(R.id.episode_list);

        //TODO glide测试
//        Glide.with(this)
//                .load(ResourceUtil.getImgNetPath("faker.jpg"))
//                .centerCrop()
//                .into(ivLogo);
    }

    public void initPlayer(){
        audioListManager = AudioListManager.getInstance();
        audioListManager.setCompleteNext(this);
    }

    //layBottom 点击事件中每次刷新列表后展示
    public void setHiddenPlayList(){
        episodeList = audioListManager.getList();

        Log.d(TAG, "Hidden List: " + episodeList.size());

        //设置rcycView
        rcycView.setItemViewSwipeEnabled(true); // 侧滑删除，默认关闭。

        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL, false);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        rcycView.setLayoutManager(manager);

        EpisodeAdapter adapter = new EpisodeAdapter(this, episodeList);
        rcycView.setAdapter(adapter);

        OnItemMoveListener mItemMoveListener = new OnItemMoveListener() {
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

                audioListManager.removeData(position);
                adapter.notifyItemRemoved(position);

                EventBus.getDefault().postSticky(new MsgRemoveInList());
            }
        };

        rcycView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI
    }

    public void setEpisodeListSheet(){
        //获取播放列表视图
//        episodeListView = findViewById(R.id.bottom_sheet);
//        episodeListView = getLayoutInflater().inflate(R.layout.episode_list_activity, null);
//        sheetView = episodeListView.findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(episodeListView);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        //获取数据
//        episodeList = new ArrayList<>();
//        for (int i = 0; i < 9; i++){
//
//            Episode item = new Episode(i, "title", "user_name1", 237, null, "/song/song1.mp3");
//            episodeList.add(item);
//        }

//        episodeList = audioListManager.getList();
//
//        Log.d(TAG, "" + episodeList.size());
//
//        //设置视图内列表
//        rcycView = episodeListView.findViewById(R.id.episode_list);
//        rcycView.setItemViewSwipeEnabled(true); // 侧滑删除，默认关闭。
//
////        episodeListView.post(new Runnable() {
////            @Override
////            public void run() {
////                int parentHeight = episodeListView.getHeight();
////                int mPaddingBottomValue = parentHeight - mBottomSheetBehavior.getPeekHeight();
////                //计算出mRecycleview需要距离底部的偏移量
////                rcycView.setPadding(0,0,0,mPaddingBottomValue);
////            }
////        });
//
//        //滑动监听
////        loadingMoreListener = new RecyclerView.OnScrollListener() {
////            //到达顶部和底部监听
////            @Override
////            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
////                super.onScrollStateChanged(recyclerView, newState);
////                // OnScrollListener.SCROLL_STATE_FLING; //屏幕处于甩动状态
////                // OnScrollListener.SCROLL_STATE_IDLE; //停止滑动状态
////                // OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;// 手指接触状态
////                // 记录当前滑动状态
////                if (newState == RecyclerView.SCROLL_STATE_IDLE) { //当前状态为停止滑动
////                    if (!rcycView.canScrollVertically(1)) { // 到达底部
////                        Toast.makeText(MainActivity.this, "底线", Toast.LENGTH_LONG).show();
////                    } else if (!rcycView.canScrollVertically(-1)) { // 到达顶部
////                        Toast.makeText(MainActivity.this, "到顶了", Toast.LENGTH_LONG).show();
////                    }
////                }
////            }
////            //上滑下滑监听
////            @Override
////            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
////                super.onScrolled(recyclerView, dx, dy);
////                if (dy < 0) { // 当前处于上滑状态
////                    Log.d(TAG, "up");
////                } else if (dy > 0) { // 当前处于下滑状态
////                    Log.d(TAG, "down");
////                }
////            }
////        };
////        rcycView.addOnScrollListener(loadingMoreListener);
//
//
//        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL, false);
//        manager.setSmoothScrollbarEnabled(true);
//        manager.setAutoMeasureEnabled(true);
//        rcycView.setLayoutManager(manager);
//
//
//        EpisodeAdapter adapter = new EpisodeAdapter(this, episodeList);
//        rcycView.setAdapter(adapter);
//
//        OnItemMoveListener  mItemMoveListener = new OnItemMoveListener() {
//            @Override
//            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
//                return false;
//            }
//
//            @Override
//            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
//                // 此方法在Item在侧滑删除时被调用。
//                // 从数据源移除该Item对应的数据，并刷新Adapter。
//                int position = srcHolder.getAdapterPosition();
//                Log.d(TAG, "remove at " + position);
//
//                audioListManager.removeData(position);
//
//                adapter.notifyItemRemoved(position);
//            }
//        };
//
//        rcycView.setOnItemMoveListener(mItemMoveListener);// 监听拖拽，更新UI

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.iv_logo){
            //属于laybottom
            if(curEpisode == null){
                Toast.makeText(this, "当前播放列表为空", Toast.LENGTH_SHORT).show();
                return;
            }

            EventBus.getDefault().postSticky(new MsgAddToAudioList(curEpisode));
            // 跳转到播放页
            AudioActivity.startAction(this);

        }else if(id == R.id.btn_play){
            //属于laybottom
            if(curEpisode == null){
                Toast.makeText(this, "不可播放", Toast.LENGTH_SHORT).show();
                return;
            }

            if(btnPlay.getTag().equals("btn_play")){
                Log.d(TAG, "laybottom play");

                btnPlay.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_pause, null));
                audioListManager.play(curEpisode);
//                    publishProgress();

                btnPlay.setTag("btn_pause");
            }else{
                Log.d(TAG, "laybottom pause");

                btnPlay.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_play, null));
                audioListManager.pause();
//                    stopPublishProgress();

                btnPlay.setTag("btn_play");
            }

        }
    }

    @Override
    public void onCompleteForNext(Episode episode) {
//        TODO
        Glide.with(this)
                .load(ResourceUtil.getPodcastPosterPath(episode.getPoster()))
                .centerCrop()
                .into(ivLogo);
//        ivLogo.setImageResource();
        tvSongName.setText(episode.getTitle());

        btnPlay.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icon_pause, null));
        btnPlay.setTag("btn_pause");

        curEpisode = episode;

        setHiddenPlayList();
    }
}