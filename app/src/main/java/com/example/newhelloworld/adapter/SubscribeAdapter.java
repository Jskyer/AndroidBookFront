package com.example.newhelloworld.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newhelloworld.R;
import com.example.newhelloworld.activity.PageActivity;
import com.example.newhelloworld.activity.SubscribeActicity;
import com.example.newhelloworld.event.MsgToAlbum;
import com.example.newhelloworld.pojo.SubscribeInfo;
import com.example.newhelloworld.util.ResourceUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SubscribeAdapter extends RecyclerView.Adapter<SubscribeAdapter.ViewHolder>{
    private List<SubscribeInfo> albums;
    private Context context;

    public SubscribeAdapter(Context context,List<SubscribeInfo> albums){
        this.context=context;
        this.albums=albums;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView album_poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            album_poster=itemView.findViewById(R.id.sub_po);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscribe_temp, parent, false);
        SubscribeAdapter.ViewHolder holder = new SubscribeAdapter.ViewHolder(inflate);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubscribeInfo subscribeInfo=albums.get(position);
        String poster=subscribeInfo.getPodcast_poster();
        Glide.with(context)
                .load(ResourceUtil.getPodcastPosterPath(poster))
                .centerCrop()
                .into(holder.album_poster);
        holder.album_poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MsgToAlbum(subscribeInfo.getAlbum_id()));
                PageActivity.startAction(context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }
}
