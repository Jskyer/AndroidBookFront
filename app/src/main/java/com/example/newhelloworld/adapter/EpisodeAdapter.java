package com.example.newhelloworld.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.model.Episode;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {
    private List<Episode> episodeList;

    static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView avatarView;
        View lineView;
        TextView titleView;

//        MaterialButton button

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lineView = itemView.findViewById(R.id.episode_line);
            titleView = itemView.findViewById(R.id.episode_name);
            lineView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("episode_item", "ok");
                }
            });
        }
    }

    public EpisodeAdapter(List<Episode> episodeList){
        this.episodeList = episodeList;
    }

    @NonNull
    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_item, parent, false);
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
}
