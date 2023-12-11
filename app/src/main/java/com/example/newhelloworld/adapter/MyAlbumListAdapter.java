package com.example.newhelloworld.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.activity.PageActivity;

import com.example.newhelloworld.pojo.Album;
import com.google.android.material.button.MaterialButton;

import java.util.List;

// 个人 创作，我的专栏adapter
public class MyAlbumListAdapter extends RecyclerView.Adapter<MyAlbumListAdapter.ViewHolder> {
    private List<Album> albumList;

    private Context context;

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

    public MyAlbumListAdapter(List<Album> albumList, Context context){
        this.albumList = albumList;
        this.context = context;
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
                PageActivity.startAction(context);
//                activity.startActivity(new Intent(activity, PageActivity.class));
            }
        });

        //TODO imgView


        holder.titleView.setText(album.getAlbum_name());
        holder.createTimeView.setText(album.getUpload_time());
        holder.countView.setText("订阅数: " + album.getSubscribe_number());

        //TODO 删除监听
    }

    @Override
    public int getItemCount() {
        return albumList.size();
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
