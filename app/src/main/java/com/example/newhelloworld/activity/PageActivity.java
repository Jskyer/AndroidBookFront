package com.example.newhelloworld.activity;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newhelloworld.R;
import com.example.newhelloworld.adapter.PageAdapter;
import com.example.newhelloworld.event.MsgAddToAudioList;
import com.example.newhelloworld.event.MsgToAlbum;
import com.example.newhelloworld.event.MsgToComment;
import com.example.newhelloworld.manager.AudioListManager;
import com.example.newhelloworld.manager.MyActivityManager;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.model.PodcastEpisode;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.Album;
import com.example.newhelloworld.pojo.Podcast;
import com.example.newhelloworld.pojo.PodcastDo;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.album.GetAlbumInfoResp;
import com.example.newhelloworld.queryVO.signIn.ResetPassResp;
import com.example.newhelloworld.queryVO.userInfo.IntegerResp;
import com.example.newhelloworld.util.ModelUtil;
import com.example.newhelloworld.util.ResourceUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PageActivity extends AppCompatActivity {
    public static final String TAG="PageActivity";

    private ImageButton button;
    private ImageButton btn_comment;
    private ImageButton btn_addfirstPlay;
    private TextView albumNameView;
    private TextView uploadTimeView;

    private TextView uploaderNameView;
    private TextView descripView;
    private ImageView albumPosterView;
    private ImageButton subscribeBtnView;
    private List<PodcastDo> podcasts;
    private MyRetrofitClient client;
    private int pageNum = 1;
    private int pageSize = 6;
    private Integer album_id=0;
    private PageAdapter adapter;
    private RecyclerView rcycView;

    private Album album;

    /*页面显示*/

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onAlbumReceived(MsgToAlbum msg){
        Log.d(TAG,"onAlbumReceived");
        album_id=msg.getAlbumId();
        getAlbumById();

        getIsSubscribe();

        EventBus.getDefault().removeStickyEvent(this);
    }

    private void getIsSubscribe(){
        client.getSubscribe(album_id, new MyObserver<IntegerResp>() {
            @Override
            public void onSuccss(IntegerResp integerResp) {
                Status status = integerResp.getStatus();
                if (status.getCode() == 200) {
                    if(integerResp.getNum() == 1){
                        subscribeBtnView.setImageResource(R.drawable.sub_light);
                    }
                }
            }
        });

    }

    private void getAlbumById() {
        client.getAlbumPreviews(album_id, pageNum, pageSize, new MyObserver<GetAlbumInfoResp>() {
            @Override
            public void onSuccss(GetAlbumInfoResp getAlbumInfoResp) {
                Status status=getAlbumInfoResp.getStatus();
                if(status.getCode()==200){
                    album=getAlbumInfoResp.getAlbum();
                    albumNameView=findViewById(R.id.page_album_name);
                    uploadTimeView=findViewById(R.id.page_uploadtime);
                    uploaderNameView=findViewById(R.id.page_uploader_name);
                    descripView=findViewById(R.id.page_description);
                    albumPosterView=findViewById(R.id.page_album_poster);

                    albumNameView.setText(album.getAlbum_name());
                    uploadTimeView.setText(album.getUpload_time());
                    uploaderNameView.setText("关注数:"+album.getSubscribe_number());
                    descripView.setText(album.getDescription());
                    Glide.with(PageActivity.this)
                            .load(ResourceUtil.getAlbumPosterPath(album.getAlbum_poster()))
                            .centerCrop()
                            .into(albumPosterView);

                    podcasts=getAlbumInfoResp.getPodcasts();
                    adapter=new PageAdapter(album,podcasts,PageActivity.this);
                    rcycView.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        MyActivityManager.getInstance().add(this);

        button=(ImageButton) findViewById(R.id.btn_back);
        subscribeBtnView=(ImageButton)findViewById(R.id.subscribe_button);
        btn_addfirstPlay=(ImageButton)findViewById(R.id.imageButton3);
        client=new MyRetrofitClient();
        podcasts=new ArrayList<>();

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
        subscribeBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                client.getSubscribe(album_id, new MyObserver<IntegerResp>() {
                    @Override
                    public void onSuccss(IntegerResp integerResp) {
                        Status status=integerResp.getStatus();
                        if(status.getCode()==200){

                            Integer is_subscribe=0;
                            is_subscribe=integerResp.getNum();
                            if(is_subscribe==0){
                                Log.d(TAG,"新增关注"+album_id.toString());
                                client.AlbumSubscribe(album_id, new MyObserver<ResetPassResp>() {
                                    @Override
                                    public void onSuccss(ResetPassResp resetPassResp) {
                                        Status status=resetPassResp.getStatus();
                                        if(status.getCode()==200){
                                            Log.d(TAG,"关注"+album_id.toString()+"成功");

                                            // 变高亮
                                            subscribeBtnView.setImageResource(R.drawable.sub_light);

                                            int cursub = album.getSubscribe_number() + 1;
                                            album.setSubscribe_number(cursub);
                                            uploaderNameView.setText("关注数:"+cursub);
//                                            flush(rcycView);
                                        }
                                    }
                                });
                            }else{
                                Log.d(TAG,"取消关注"+album_id.toString());
                                client.cancelSubscribe(album_id, new MyObserver<ResetPassResp>() {
                                    @Override
                                    public void onSuccss(ResetPassResp resetPassResp) {
                                        Status status=resetPassResp.getStatus();
                                        if(status.getCode()==200){
                                            Log.d(TAG,"取消关注"+album_id.toString()+"成功");

                                            // 变默认
                                            subscribeBtnView.setImageResource(R.drawable.sub_default);

                                            int cursub = album.getSubscribe_number() - 1;
                                            album.setSubscribe_number(cursub);
                                            uploaderNameView.setText("关注数:"+cursub);
//                                            flush(rcycView);
                                        }
                                    }
                                });
                            }

                        }
                    }
                });

            }
        });
        btn_addfirstPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Episode episode1 = ModelUtil.transEpisode(podcasts.get(0));
                AudioListManager.getInstance().addData(episode1);
                EventBus.getDefault().postSticky(new MsgAddToAudioList(episode1));
                AudioActivity.startAction(PageActivity.this);
            }
        });
        // 创建图像数组和文本数组
        getAlbumById();
        rcycView = findViewById(R.id.album_episode_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);
        EventBus.getDefault().register(this);
    }
/*    public void initData(){
        albumPodcastEpisodeList = new ArrayList<>();
        for (int i = 1; i < 20; i++){
            PodcastEpisode item = new PodcastEpisode("title",
                    "description",
                    i, LocalDateTime.now());
            albumPodcastEpisodeList.add(item);
        }
    }*/
    public static void startAction(Context context){
        Intent intent = new Intent(context, PageActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
//    public void flush(View view) {
//        finish();
//        PageActivity.startAction(this);
//    }
}
