package com.example.newhelloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.newhelloworld.activity.PageActivity;
import com.example.newhelloworld.activity.SubscribeActicity;
import com.example.newhelloworld.adapter.CustomAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class BFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        ListView listView = view.findViewById(R.id.list_view);

        // 创建图像数组和文本数组
        Integer[] imageArray = {R.drawable.guolai, R.drawable.guolai, R.drawable.guolai,R.drawable.guolai, R.drawable.guolai, R.drawable.guolai}; // 替换为您的图片资源
        String[] textArray = {"Column 1", "Column 2", "Column 3","Column 4", "Column 5", "Column 6"}; // 栏目名
        String[] textArrayP = {"P 1", "P 2", "P 3","P 1", "P 2", "P 3"};
        String[] textArrayR = {"Recommend 1", "Recommend 2", "Recommend 3","Recommend 1", "Recommend 2", "Recommend 3"};

        // 创建自定义适配器
        CustomAdapter adapter = new CustomAdapter(getActivity(), textArray, textArrayP, textArrayR, imageArray);
        listView.setAdapter(adapter);
        FloatingActionButton fab=view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SubscribeActicity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener((adapterView, view1, position, l) -> {
            // 根据点击的位置跳转到相应的页面
            // 这里具体页面还没写，所以还没完善这里
            switch (position) {
                case 0:
                    // 跳转到column 1
                    startActivity(new Intent(getActivity(), PageActivity.class));
                    break;
                case 1:
                    // 跳转到column 2
                    startActivity(new Intent(getActivity(), PageActivity.class));
                    break;
                case 2:
                    // 跳转到column 3
                    startActivity(new Intent(getActivity(), PageActivity.class));
                    break;
                case 3:
                    // 跳转到column 4
                    startActivity(new Intent(getActivity(), PageActivity.class));
                    break;
                case 4:
                    // 跳转到column 5
                    startActivity(new Intent(getActivity(), PageActivity.class));
                    break;
                case 5:
                    // 跳转到column 6
                    startActivity(new Intent(getActivity(), PageActivity.class));
                    break;
            }
        });

        return view;
    }



}




