package com.example.newhelloworld.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newhelloworld.R;

public class CustomAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final Integer[] imageArray;
    private final String[] textArray;

    private final String[] textArrayP;

    private final String[] textArrayR;

    public CustomAdapter(Activity context, String[] textArrayParam, String[] textArrayPart, String[] textArrayRecommend, Integer[] imageArrayParam) {
        super(context, R.layout.a_fragment_unit, textArrayParam);

        this.context = context;
        this.imageArray = imageArrayParam;
        //栏目名
        this.textArray = textArrayParam;
        //集数
        this.textArrayP = textArrayPart;
        //评论
        this.textArrayR = textArrayRecommend;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.a_fragment_unit, null, true);

        TextView text = rowView.findViewById(R.id.text1);
        TextView text2 = rowView.findViewById(R.id.text2);
        TextView text3 = rowView.findViewById(R.id.text3);
        ImageView image = rowView.findViewById(R.id.image);

        text.setText(textArray[position]);
        text2.setText(textArrayP[position]);
        text3.setText(textArrayR[position]);
        image.setImageResource(imageArray[position]);
        return rowView;
    }
}

