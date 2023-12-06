package com.example.newhelloworld.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.activity.AudioActivity;
import com.example.newhelloworld.event.MsgAddToAudioList;
import com.example.newhelloworld.event.MsgAudioToMain;
import com.example.newhelloworld.event.MsgPlaylistToMain;
import com.example.newhelloworld.manager.AudioListManager;
import com.example.newhelloworld.model.Episode;
import com.google.android.material.button.MaterialButton;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

// 播放列表中 单集adpater
public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {
    private Context context;
    private List<Episode> episodeList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        View itemView;

        ImageView imgView;
        TextView titleView;
        MaterialButton play_btn;

//        LinearLayout delBlockView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            this.itemView = itemView;
//            this.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d("EpisodeAdapter", "ok");
//                }
//            });

            imgView = itemView.findViewById(R.id.playlst_poster);
            titleView = itemView.findViewById(R.id.playlst_title);
            play_btn = itemView.findViewById(R.id.playlst_btn);
        }

//        public LinearLayout getDelBlockView(){
//            return delBlockView;
//        }
    }

    public EpisodeAdapter(Context context, List<Episode> episodeList){
        this.context = context;
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

        //TODO
        //        holder.imgView.setImageResource();
        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new MsgAddToAudioList(episode));
                AudioActivity.startAction(context);
            }
        });

        AudioListManager instance = AudioListManager.getInstance();

        if(instance.isPlayManagerPlaying() && instance.getCurpos() == position){
            holder.play_btn.setIcon(ResourcesCompat.getDrawable(context.getResources(), R.drawable.icon_pause, null));
            holder.play_btn.setTag("btn_pause");
        }

        holder.play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getTag().equals("btn_play")){
                    Log.d("EpisodeAdapter", "to play");

//                    holder.play_btn.setIcon(ResourcesCompat.getDrawable(context.getResources(), R.drawable.icon_pause, null));
//                    view.setTag("btn_pause");

                    instance.play(episode);

                    EventBus.getDefault().postSticky(new MsgPlaylistToMain(episode, true));
//                    publishProgress();
                }else{
                    Log.d("EpisodeAdapter", "to pause");

//                    holder.play_btn.setIcon(ResourcesCompat.getDrawable(context.getResources(), R.drawable.icon_play, null));
//                    view.setTag("btn_play");

                    instance.pause();

                    EventBus.getDefault().postSticky(new MsgPlaylistToMain(episode, false));
//                    stopPublishProgress();
                }
            }
        });
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
