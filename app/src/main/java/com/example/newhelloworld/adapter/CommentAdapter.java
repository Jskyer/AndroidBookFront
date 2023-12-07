package com.example.newhelloworld.adapter;



import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.model.Comment;
import com.example.newhelloworld.model.PodcastEpisode;

import java.io.File;
import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private List<Comment> data;
    private Context context;
    public CommentAdapter(List<Comment> data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    //返回每一个item条目并给它进行一个设值后返回到界面上。
    //每次在显示的时候，都会调用getview给每一个item进行赋值
    public View getView(int position, View convertView, ViewGroup parent) {

        //因为item是可以复用的，上下滑动界面时会不断地进行判断，为了防止convertView不断创建，要进行判断
        if(convertView == null){
            //先拿到item条目的布局                             渲染layout.list_item,渲染后给到convertView
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item,parent,false);
        }

        //需要把data创建的那些值，传到textView每一个item上面（这个值在项目中是从网上获取的）

        TextView username = convertView.findViewById(R.id.user_name);
        ImageView avator=convertView.findViewById(R.id.avator);
        TextView content=convertView.findViewById(R.id.content);
        //getView并不是只会调用一次，而是界面每显示一个item的时候都会创建一次
        username.setText(data.get(position).getComment_text());//这样就把name设置到了textview上面
        avator.setImageURI(Uri.fromFile(new File("C:\\Users\\catHolic\\Desktop\\android-front\\AudioBook_frontend\\app\\src\\main\\res\\drawable\\bg2.png")));
        content.setText(data.get(position).getComment_text());
        Log.e("zhua", "getView: "+position);

        return convertView;
    }
}