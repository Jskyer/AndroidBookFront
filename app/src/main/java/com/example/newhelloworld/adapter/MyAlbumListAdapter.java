package com.example.newhelloworld.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.activity.PageActivity;
import com.example.newhelloworld.model.Album;

import java.time.format.DateTimeFormatter;
import java.util.List;

// 个人 创作，我的专栏adapter
public class MyAlbumListAdapter extends RecyclerView.Adapter<MyAlbumListAdapter.ViewHolder> {
    private List<Album> albumList;

    private Activity activity;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        //        ImageView imgView;
        TextView titleView;
        TextView createTimeView;
        TextView countView;

//        MaterialButton button

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            titleView = itemView.findViewById(R.id.album_name);
            createTimeView = itemView.findViewById(R.id.create_time);
            countView = itemView.findViewById(R.id.count_subscribe);
        }
    }

    public MyAlbumListAdapter(List<Album> albumList, Activity activity){
        this.albumList = albumList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyAlbumListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.contribution_item, parent, false);
        MyAlbumListAdapter.ViewHolder holder = new MyAlbumListAdapter.ViewHolder(inflate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PageActivity.startAction(activity);
//                activity.startActivity(new Intent(activity, PageActivity.class));
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAlbumListAdapter.ViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.titleView.setText(album.getTitle());
        holder.createTimeView.setText("" + album.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy/mm/dd")));
        holder.countView.setText("订阅数: " + album.getCount());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
