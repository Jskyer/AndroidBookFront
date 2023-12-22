package com.example.newhelloworld.adapter;

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
import com.example.newhelloworld.event.MsgAddToAudioList;
import com.example.newhelloworld.manager.AudioListManager;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.pojo.HistoryInfo;
import com.example.newhelloworld.pojo.PodcastDo;
import com.example.newhelloworld.util.ModelUtil;
import com.example.newhelloworld.util.ResourceUtil;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    private List<PodcastDo> podcasts;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView imageView;
        TextView titleView;
        TextView uploaderView;
        //        TextView viewsView;
        TextView dateView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.item_img);
            titleView = itemView.findViewById(R.id.item_title);
            uploaderView = itemView.findViewById(R.id.item_uploader);
//            viewsView = itemView.findViewById(R.id.item_views);
            dateView = itemView.findViewById(R.id.item_date);
        }
    }

    public SearchAdapter(List<PodcastDo> podcasts, Context context) {
        this.podcasts=podcasts;
        this.context=context;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personal_history, parent, false);
        SearchAdapter.ViewHolder holder = new SearchAdapter.ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        PodcastDo podcast=podcasts.get(position);

        Glide.with(context)
                .load(ResourceUtil.getPodcastPosterPath(podcast.getPodcastPoster()))
                .centerCrop()
                .into(holder.imageView);

        holder.titleView.setText(podcast.getTitle());
        holder.uploaderView.setText(podcast.getUploaderName());
//        holder.viewsView.setText("观看 "+ episode.getViews() + " 次");

        holder.dateView.setText(podcast.getCreateTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Episode episode1 = ModelUtil.transEpisode(podcast);
                AudioListManager.getInstance().addData(episode1);
                EventBus.getDefault().postSticky(new MsgAddToAudioList(episode1));
                AudioActivity.startAction(context);
            }
        });


    }

    @Override
    public int getItemCount() {
        return podcasts.size();
    }

    public void updateList(List<PodcastDo> newDatas) {
        // 在原有的数据之上增加新数据
        if (newDatas != null && newDatas.size() > 0) {
            podcasts.addAll(newDatas);
            notifyDataSetChanged();
        }

    }
}
