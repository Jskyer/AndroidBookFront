package com.example.newhelloworld.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newhelloworld.MyApplication;
import com.example.newhelloworld.R;
import com.example.newhelloworld.activity.AudioActivity;
import com.example.newhelloworld.event.MsgAddToAudioList;
import com.example.newhelloworld.manager.AudioListManager;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.pojo.HistoryInfo;
import com.example.newhelloworld.util.ModelUtil;
import com.example.newhelloworld.util.ResourceUtil;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

// 个人 浏览历史adapter
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<HistoryInfo> episodeList;

    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView imageView;
        TextView titleView;
        TextView uploaderView;
        TextView viewsView;
        TextView dateView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.item_img);
            titleView = itemView.findViewById(R.id.item_title);
            uploaderView = itemView.findViewById(R.id.item_uploader);
            viewsView = itemView.findViewById(R.id.item_views);
            dateView = itemView.findViewById(R.id.item_date);
        }
    }

    public HistoryAdapter(List<HistoryInfo> episodeList, Context context) {
        this.episodeList = episodeList;
        this.context = context;
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
        HistoryInfo episode = episodeList.get(position);

        Glide.with(context)
                .load(ResourceUtil.getPodcastPosterPath(episode.getPodcast_poster()))
                .centerCrop()
                .into(holder.imageView);

        holder.titleView.setText(episode.getTitle());
        holder.uploaderView.setText(episode.getUploader_name());
        holder.viewsView.setText("观看 "+ episode.getViews() + " 次");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 定义日期格式
        holder.dateView.setText(sdf.format(episode.getDatetime()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EventBus.getDefault().postSticky(new MsgAddToAudioList(episode));

                //TODO
                Episode episode1 = ModelUtil.transEpisode(episode);
                AudioListManager.getInstance().addData(episode1);
                EventBus.getDefault().postSticky(new MsgAddToAudioList(episode1));
                AudioActivity.startAction(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    // 暴露接口，更新数据源
    public void updateList(List<HistoryInfo> newDatas) {
        // 在原有的数据之上增加新数据
        if (newDatas != null && newDatas.size() > 0) {
            episodeList.addAll(newDatas);
            notifyDataSetChanged();
        }

    }



}
