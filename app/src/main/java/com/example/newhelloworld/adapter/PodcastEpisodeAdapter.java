package com.example.newhelloworld.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.activity.AudioActivity;
import com.example.newhelloworld.activity.CommentActivity;
import com.example.newhelloworld.model.PodcastEpisode;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PodcastEpisodeAdapter extends RecyclerView.Adapter<PodcastEpisodeAdapter.ViewHolder> {
    private List<PodcastEpisode> podcastEpisodeList;
    private Activity activity;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView contentView;
        TextView timeView;
        TextView dateView;
        ImageView btn_comment;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.item_title);
            contentView = itemView.findViewById(R.id.item_content);
            timeView = itemView.findViewById(R.id.item_leftTime);
            dateView = itemView.findViewById(R.id.item_date);
            btn_comment=itemView.findViewById(R.id.item_comment);

        }
    }

    public PodcastEpisodeAdapter(List<PodcastEpisode> podcastEpisodeList,Activity activity) {
        this.podcastEpisodeList = podcastEpisodeList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public PodcastEpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_podcast_episode, parent, false);
        PodcastEpisodeAdapter.ViewHolder holder = new PodcastEpisodeAdapter.ViewHolder(inflate);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioActivity.startAction(activity);
//                activity.startActivity(new Intent(activity, PageActivity.class));
            }
        });
        holder.btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentActivity.startAction(activity);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastEpisodeAdapter.ViewHolder holder, int position) {
        PodcastEpisode podcastEpisode = podcastEpisodeList.get(position);
        holder.titleView.setText(podcastEpisode.getTitle());
        holder.contentView.setText(podcastEpisode.getContent());
        holder.timeView.setText("剩余 "+ podcastEpisode.getLeftTime() + " 分钟");
        holder.dateView.setText(podcastEpisode.getDateTime().format(DateTimeFormatter.ofPattern("yyyy/mm/dd")));
    }

    @Override
    public int getItemCount() {
        return podcastEpisodeList.size();
    }
}
