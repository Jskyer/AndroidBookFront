package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newhelloworld.R;
import com.example.newhelloworld.adapter.CommentAdapter;
import com.example.newhelloworld.event.MsgToComment;
import com.example.newhelloworld.model.Comment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    public static final String TAG = "CommentActivity";

    private ImageButton button;
    private List<Comment> commentList=new ArrayList<>();

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveId(MsgToComment msg){
        Log.d(TAG, "onReceiveId");

        int podcastId = msg.getPodcastId();
        //getComments(podcastId);

        EventBus.getDefault().removeStickyEvent(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_lv);
        ListView view=(ListView) findViewById(R.id.comment_list_view);
        button=findViewById(R.id.btn_back_comment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 创建图像数组和文本数组
        initData();
        view.setAdapter(new CommentAdapter(commentList,this));

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    public void initData(){
        for(int i=0;i<20;i++){
            Comment comment=new Comment();
            comment.setComment_id(1);
            comment.setCommenter_id(1);
            comment.setComment_text("hhh");
            comment.setComment_time(new Date());
            comment.setLikeNum(1);
            comment.setPodcastId(1);
            commentList.add(comment);
        }
    }
    public static void startAction(Context context){
        Intent intent = new Intent(context, CommentActivity.class);
        context.startActivity(intent);
    }


}
