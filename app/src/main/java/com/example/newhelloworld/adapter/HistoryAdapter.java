package com.example.newhelloworld.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.model.Episode;

import java.time.format.DateTimeFormatter;
import java.util.List;

// 个人 浏览历史adapter
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<Episode> episodeList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView contentView;
        TextView timeView;
        TextView dateView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.item_title);
            contentView = itemView.findViewById(R.id.item_content);
            timeView = itemView.findViewById(R.id.item_leftTime);
            dateView = itemView.findViewById(R.id.item_date);
        }
    }

    public HistoryAdapter(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personal_history, parent, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        Episode episode = episodeList.get(position);
        holder.titleView.setText(episode.getTitle());
        holder.contentView.setText(episode.getContent());
        holder.timeView.setText("剩余 "+ episode.getLeftTime() + " 分钟");
        holder.dateView.setText(episode.getDateTime().format(DateTimeFormatter.ofPattern("yyyy/mm/dd")));
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }
}
