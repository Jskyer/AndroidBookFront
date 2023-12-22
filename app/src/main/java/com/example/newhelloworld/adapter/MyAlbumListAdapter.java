package com.example.newhelloworld.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newhelloworld.R;
import com.example.newhelloworld.activity.MyAlbumListActivity;
import com.example.newhelloworld.activity.PageActivity;

import com.example.newhelloworld.event.MsgToAlbum;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.Album;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.signIn.ResetPassResp;
import com.example.newhelloworld.util.ResourceUtil;
import com.google.android.material.button.MaterialButton;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

// 个人 创作，我的专栏adapter
public class MyAlbumListAdapter extends RecyclerView.Adapter<MyAlbumListAdapter.ViewHolder> {
    private List<Album> albumList;

    private Context context;

    private MyRetrofitClient client;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView imgView;
        TextView titleView;
        TextView createTimeView;
        TextView countView;

        MaterialButton button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            imgView = itemView.findViewById(R.id.album_img);
            titleView = itemView.findViewById(R.id.album_name);
            createTimeView = itemView.findViewById(R.id.create_time);
            countView = itemView.findViewById(R.id.count_subscribe);
            button = itemView.findViewById(R.id.btn_album_del);
        }
    }

    public MyAlbumListAdapter(List<Album> albumList, Context context, MyRetrofitClient client){
        this.albumList = albumList;
        this.context = context;
        this.client = client;
    }

    @NonNull
    @Override
    public MyAlbumListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.contribution_item, parent, false);
        MyAlbumListAdapter.ViewHolder holder = new MyAlbumListAdapter.ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAlbumListAdapter.ViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new MsgToAlbum(album.getAlbum_id()));
                PageActivity.startAction(context);
            }
        });


        Glide.with(context)
                .load(ResourceUtil.getAlbumPosterPath(album.getAlbum_poster()))
                .centerCrop()
                .into(holder.imgView);

        holder.titleView.setText(album.getAlbum_name());
        holder.createTimeView.setText(album.getUpload_time());
        holder.countView.setText("订阅数: " + album.getSubscribe_number());

        int pos = position;

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("是否取消订阅这个专辑")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //不等待后端返回，直接删除前端显示的item，及时响应用户
                                delItemInList(pos);

                                client.cancelSubscribe(album.getAlbum_id(), new MyObserver<ResetPassResp>() {
                                    @Override
                                    public void onSuccss(ResetPassResp resp) {
                                        Status status = resp.getStatus();

                                        if(status.getCode() == 200){
                                            Log.d("MyAlbumListActivity","cancelSubscribe ok");
                                        }else{
                                            Log.d("MyAlbumListActivity","cancelSubscribe error: " + status.getMsg());
                                        }
                                    }
                                });

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Log.d("MyAlbumListActivity", "close");
                            }
                        }).create();

                dialog.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


    private void delItemInList(int position){
        albumList.remove(position);
        notifyDataSetChanged();
    }


    // 暴露接口，更新数据源
    public void updateList(List<Album> newDatas) {
        // 在原有的数据之上增加新数据
        if (newDatas != null && newDatas.size() > 0) {
            albumList.addAll(newDatas);
            notifyDataSetChanged();
        }

    }
}
