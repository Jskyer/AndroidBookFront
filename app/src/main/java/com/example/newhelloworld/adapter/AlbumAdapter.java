package com.example.newhelloworld.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newhelloworld.R;
import com.example.newhelloworld.pojo.Album;
import com.example.newhelloworld.util.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 暂未使用
 */
public class AlbumAdapter extends ArrayAdapter<String> {
    private Activity context;
    private List<Album> albums;


    public AlbumAdapter(Activity context, List<Album> albums) {
        super(context, R.layout.fragment_simple_item);

        this.context = context;
        this.albums = albums;
    }

    public View getView(int position, View view, ViewGroup parent) {

        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_simple_item,  parent,false);
//        LayoutInflater inflater = context.getLayoutInflater();
//        View rowView = inflater.inflate(android.R.layout.simple_list_item_1, null, true);

        TextView text = rowView.findViewById(R.id.single_text);

        text.setText(albums.get(position).getAlbum_name());
//        text2.setText(textArrayP[position]);
//        text3.setText(textArrayR[position]);
//        Glide.with(context)
//                .load(ResourceUtil.getPodcastPosterPath(imageArray[position]))
//                .centerCrop()
//                .into(image);

        return rowView;
    }

}
