package com.example.newhelloworld.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newhelloworld.R;

public class CFragmentAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final Integer[] imageArray;
    private final String[] textArray;

    public CFragmentAdapter(Activity context, String[] textArrayParam, Integer[] imageArrayParam) {
        super(context, R.layout.a_fragment_unit, textArrayParam);

        this.context = context;
        this.imageArray = imageArrayParam;
        //栏目名
        this.textArray = textArrayParam;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_personal, null, true);

        TextView text = rowView.findViewById(R.id.item_text);
        ImageView image = rowView.findViewById(R.id.item_img);

        text.setText(textArray[position]);
        image.setImageResource(imageArray[position]);
        return rowView;
    }
}
