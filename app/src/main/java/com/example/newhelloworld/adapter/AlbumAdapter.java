package com.example.newhelloworld.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newhelloworld.R;
import com.example.newhelloworld.activity.AudioActivity;
import com.example.newhelloworld.activity.PageActivity;
import com.example.newhelloworld.event.MsgAddToAudioList;
import com.example.newhelloworld.event.MsgToAlbum;
import com.example.newhelloworld.manager.AudioListManager;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.pojo.Album;
import com.example.newhelloworld.pojo.HistoryInfo;
import com.example.newhelloworld.util.ModelUtil;
import com.example.newhelloworld.util.ResourceUtil;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于pojo List<Album>
 *  已在 CategoryActivity 使用
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
//    private Activity context;
    private List<Album> albums;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView imageView;
        TextView titleView;
        TextView uploadView;
        TextView typeView;
        TextView subView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;

            imageView = itemView.findViewById(R.id.item_img);
            titleView = itemView.findViewById(R.id.item_title);
            uploadView = itemView.findViewById(R.id.item_content);
            typeView = itemView.findViewById(R.id.item_leftTime);
            subView = itemView.findViewById(R.id.item_date);
        }
    }

    public AlbumAdapter(List<Album> albums, Context context) {
        this.albums = albums;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albums.get(position);

        Glide.with(context)
                .load(ResourceUtil.getAlbumPosterPath(album.getAlbum_poster()))
                .centerCrop()
                .into(holder.imageView);

        holder.titleView.setText(album.getAlbum_name());
        holder.uploadView.setText("上传时间: "+album.getUpload_time());
        holder.typeView.setText("分类: "+ album.getType());
        holder.subView.setText("订阅数: "+ album.getSubscribe_number());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new MsgToAlbum(album.getAlbum_id()));
                PageActivity.startAction(context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }


//    public AlbumAdapter(Activity context, List<Album> albums) {
//        super(context, R.layout.fragment_simple_item);
//
//        this.context = context;
//        this.albums = albums;
//    }
//
//    public View getView(int position, View view, ViewGroup parent) {
//
//        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_simple_item,  parent,false);
////        LayoutInflater inflater = context.getLayoutInflater();
////        View rowView = inflater.inflate(android.R.layout.simple_list_item_1, null, true);
//
//        TextView text = rowView.findViewById(R.id.single_text);
//
//        text.setText(albums.get(position).getAlbum_name());
////        text2.setText(textArrayP[position]);
////        text3.setText(textArrayR[position]);
////        Glide.with(context)
////                .load(ResourceUtil.getPodcastPosterPath(imageArray[position]))
////                .centerCrop()
////                .into(image);
//
//        return rowView;
//    }

}
