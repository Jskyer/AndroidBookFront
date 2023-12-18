

package com.example.newhelloworld.activity;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageButton;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.newhelloworld.R;
        import com.example.newhelloworld.adapter.CommentAdapter;

        import com.example.newhelloworld.event.MsgToComment;
        import com.example.newhelloworld.net.MyObserver;
        import com.example.newhelloworld.net.MyRetrofitClient;
        import com.example.newhelloworld.pojo.Comment;
        import com.example.newhelloworld.queryVO.GetCommentsResp;
        import com.example.newhelloworld.queryVO.Status;

        import org.greenrobot.eventbus.EventBus;
        import org.greenrobot.eventbus.Subscribe;
        import org.greenrobot.eventbus.ThreadMode;

        import java.util.ArrayList;
        import java.util.List;

public class CommentActivity extends AppCompatActivity {
    public static final String TAG = "CommentActivity";

    private ImageButton button;
    private List<Comment> commentList=new ArrayList<>();
    /*for adapter*/
    private List<Comment> comments=new ArrayList<>();
    private MyRetrofitClient client;
    //private ActivityCategoryBinding binding;
    private RecyclerView rcycView;


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveId(MsgToComment msg){
        Log.d(TAG, "onReceiveId");

        int podcastId = msg.getPodcastId();
        //getComments(podcastId);
        requestComment(podcastId);
        EventBus.getDefault().removeStickyEvent(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //binding=ActivityCategoryBinding.inflate(getLayoutInflater());
        client = new MyRetrofitClient();
        setContentView(R.layout.comment_lv);

        rcycView = findViewById(R.id.comment_list_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);


        button=findViewById(R.id.btn_back_comment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
    public void requestComment(int podcast_id){
        client.getComments(podcast_id, new MyObserver<GetCommentsResp>() {
            @Override
            public void onSuccss(GetCommentsResp getCommentsResp) {
                Status status=getCommentsResp.getStatus();
                if(status.getCode()==200){
                    comments=new ArrayList<>();
                    commentList=getCommentsResp.getComments();
                    for(int i=0;i<commentList.size();i++){
                        Comment comment=new Comment();
                        comment.setComment_id(commentList.get(i).getComment_id());
                        comment.setCommenter_id(commentList.get(i).getCommenter_id());
                        comment.setComment_text(commentList.get(i).getComment_text());

                        comment.setComment_time(commentList.get(i).getComment_time());
                        comment.setLikeNum(commentList.get(i).getLikeNum());
                        comment.setPodcastId(podcast_id);
                        //Log.d("podcastId",commentList.get(i).getComment_text());
                        comments.add(comment);
                    }

                    CommentAdapter adapter=new CommentAdapter(comments,CommentActivity.this);
                    rcycView.setAdapter(adapter);
                    /*for(int i=0;i<comments.size();i++){
                        String temp=comments.get(i).getComment_text();
                        Log.d("创建comment",temp);
                    }*/

                }
            }
        });
    }
    public static void startAction(Context context){
        Intent intent = new Intent(context, CommentActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
