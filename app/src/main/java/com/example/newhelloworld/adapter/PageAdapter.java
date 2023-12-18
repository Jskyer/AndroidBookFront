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
import com.example.newhelloworld.activity.AudioActivity;
import com.example.newhelloworld.activity.CommentActivity;
import com.example.newhelloworld.event.MsgAddToAudioList;
import com.example.newhelloworld.event.MsgToComment;
import com.example.newhelloworld.manager.AudioListManager;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.model.PodcastEpisode;
import com.example.newhelloworld.pojo.Album;
import com.example.newhelloworld.pojo.Podcast;
import com.example.newhelloworld.pojo.PodcastDo;
import com.example.newhelloworld.util.ModelUtil;
import com.example.newhelloworld.util.ResourceUtil;

import org.greenrobot.eventbus.EventBus;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder> {
    private Album album;
    private Activity activity;
    private List<PodcastDo> podcasts;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView contentView;
        TextView timeView;
        TextView dateView;
        ImageView podcastPoster;
        ImageView btn_comment;





        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.item_title);
            contentView = itemView.findViewById(R.id.item_content);
            timeView = itemView.findViewById(R.id.item_leftTime);
            dateView = itemView.findViewById(R.id.item_date);
            btn_comment=itemView.findViewById(R.id.item_comment);
            podcastPoster=itemView.findViewById(R.id.item_img);

        }
    }

    public PageAdapter(Album album, List<PodcastDo> podcasts,Context context) {
        this.album=album;
        this.podcasts=podcasts;
        this.context=context;
    }

    @NonNull
    @Override
    public PageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_podcast_episode, parent, false);
        PageAdapter.ViewHolder holder = new PageAdapter.ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PageAdapter.ViewHolder holder, int position) {
        PodcastDo podcast=podcasts.get(position);
        Glide.with(context)
                .load(ResourceUtil.getPodcastPosterPath(podcast.getPodcastPoster()))
                .centerCrop()
                .into(holder.podcastPoster);
        holder.titleView.setText(podcast.getTitle());
        holder.contentView.setText(podcast.getUploaderName());
        holder.timeView.setText(podcast.getDuration().toString());
        holder.dateView.setText(podcast.getCreateTime().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Episode episode1 = ModelUtil.transEpisode(podcast);
                AudioListManager.getInstance().addData(episode1);
                EventBus.getDefault().postSticky(new MsgAddToAudioList(episode1));
                AudioActivity.startAction(context);
//                activity.startActivity(new Intent(activity, PageActivity.class));
            }
        });
        holder.btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MsgToComment(podcast.getPodcastId()));
                CommentActivity.startAction(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return podcasts.size();
    }
}
