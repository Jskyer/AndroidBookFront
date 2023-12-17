package com.example.newhelloworld.adapter;



import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.Comment;
import com.example.newhelloworld.model.PodcastEpisode;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.StatusResp;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Date;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<Comment> data;
    public static final String TAG = "CommentAdapter";
    private Activity context;
    MyRetrofitClient client;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView user_name;
        TextView content;
        TextView time;
        ImageView btn_like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name=itemView.findViewById(R.id.user_name);
            time=itemView.findViewById(R.id.time);
            content=itemView.findViewById(R.id.content);
            btn_like=itemView.findViewById(R.id.like);
        }
    }

    public CommentAdapter(List<Comment> comments,Activity activity){
        this.data=comments;
        this.context=activity;

    }





    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate=LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false);
        CommentAdapter.ViewHolder holder=new CommentAdapter.ViewHolder(inflate);
        client=new MyRetrofitClient();

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment=data.get(position);

        Log.d("bind",comment.getComment_text());
        holder.user_name.setText(comment.getCommenter_id().toString());
        holder.time.setText(comment.getLikeNum().toString());
        holder.content.setText(comment.getComment_text());
        holder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.likeComment(comment.getComment_id(), new MyObserver<StatusResp>() {
                    @Override
                    public void onSuccss(StatusResp statusResp) {
                        Status status=statusResp.getStatus();
                        if(status.getCode()==200){
                            Log.d(TAG,"点赞/取消点赞成功");
                            notifyItemChanged(holder.getAdapterPosition(),null);
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}