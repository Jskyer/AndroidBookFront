package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.adapter.CommentAdapter;

import com.example.newhelloworld.event.MsgToComment;
import com.example.newhelloworld.manager.MyActivityManager;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.Comment;
import com.example.newhelloworld.queryVO.GetCommentsResp;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.StatusResp;
import com.example.newhelloworld.util.PreferenceUtil;

import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    public static final String TAG = "CommentActivity";

    private ImageButton button;
    private ImageButton addCommentBtn;
    private List<Comment> commentList = new ArrayList<>();
    private EditText edit;
    private Integer podcastId;
    private CommentAdapter adapter;

    /*for adapter*/
//    private List<Comment> comments = new ArrayList<>();

    private MyRetrofitClient client;
    //private ActivityCategoryBinding binding;
    private RecyclerView rcycView;


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveId(MsgToComment msg) {
        Log.d(TAG, "onReceiveId");

        podcastId = msg.getPodcastId();
        //getComments(podcastId);
        requestComment(podcastId);
        EventBus.getDefault().removeStickyEvent(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding=ActivityCategoryBinding.inflate(getLayoutInflater());
        client = new MyRetrofitClient();
        setContentView(R.layout.comment_lv);

        MyActivityManager.getInstance().add(this);

        rcycView = findViewById(R.id.comment_list_view);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcycView.addItemDecoration(decoration);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);



        edit = (EditText) findViewById(R.id.comment_ct);
        addCommentBtn = findViewById(R.id.send);
        button = findViewById(R.id.btn_back_comment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edit.getText().toString();
                if (StringUtils.isBlank(content)) {
                    Toast.makeText(CommentActivity.this, "不能发送空信息", Toast.LENGTH_SHORT).show();
                    return;
                }

                client.addComment(podcastId, content, new MyObserver<StatusResp>() {
                    @Override
                    public void onSuccss(StatusResp statusResp) {
                        Status status = statusResp.getStatus();
                        if (status.getCode() == 200) {
                            Log.d(TAG, "成功发送comment：" + content);

                            requestComment(podcastId);

//                            flush(rcycView);
                        } else {
                            Log.d(TAG, "发送失败");
                        }

                    }
                });

//                int userid = Integer.parseInt(PreferenceUtil.getString(CommentActivity.this, PreferenceUtil.KEY_USER_ID));
//                Comment comment = new Comment(null, podcastId, userid, content, null, 0);
//                adapter.updateList(comment);

            }
        });

        // 创建图像数组和文本数组
        //initData();
        EventBus.getDefault().register(this);

    }

    /*    public void initData(){
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
        }*/
    public void requestComment(int podcast_id) {
        client.getComments(podcast_id, new MyObserver<GetCommentsResp>() {
            @Override
            public void onSuccss(GetCommentsResp getCommentsResp) {
                Status status = getCommentsResp.getStatus();
                if (status.getCode() == 200) {

                    commentList = getCommentsResp.getComments();
                    adapter = new CommentAdapter(commentList, CommentActivity.this);
                    rcycView.setAdapter(adapter);

                    Log.d(TAG, "getSubscribePreviews ok");
                }else{
                    Log.d(TAG, "getSubscribePreviews error");
                }
            }
        });


    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, CommentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

//    public void flush(View view) {
//        finish();
//        EventBus.getDefault().postSticky(new MsgToComment(podcastId));
//        CommentActivity.startAction(this);
//
//    }
}

