package com.example.newhelloworld.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.model.Episode;
import com.google.android.material.button.MaterialButton;

import java.util.List;

// 播放列表中 单集adpater
public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {
    private List<Episode> episodeList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imgView;
        View itemView;
        TextView titleView;

        LinearLayout delBlockView;

//        MaterialButton button

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("EpisodeAdapter", "ok");
                }
            });

            titleView = itemView.findViewById(R.id.episode_name);
            delBlockView = itemView.findViewById(R.id.del_hidden);
        }

        public LinearLayout getDelBlockView(){
            return delBlockView;
        }
    }

    public EpisodeAdapter(List<Episode> episodeList){
        this.episodeList = episodeList;
    }

    @NonNull
    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_item,  parent,false);
        EpisodeAdapter.ViewHolder holder = new EpisodeAdapter.ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.ViewHolder holder, int position) {

        Episode episode = episodeList.get(position);
        holder.titleView.setText(episode.getTitle());

    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

//    public void setRecyclerManager(RecyclerView recyclerView){
//        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//        layoutManager.setSmoothScrollbarEnabled(true);
//        layoutManager.setAutoMeasureEnabled(true);
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(this);
//    }
}
