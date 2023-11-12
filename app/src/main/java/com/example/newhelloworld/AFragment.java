package com.example.newhelloworld;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.newhelloworld.activity.AudioActivity;
import com.example.newhelloworld.activity.CategoryActivity;
import com.example.newhelloworld.activity.PageActivity;
import com.example.newhelloworld.adapter.CustomAdapter;
import com.example.newhelloworld.adapter.YourPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class AFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        ListView listView = view.findViewById(R.id.list_view);

        // 创建图像数组和文本数组
        Integer[] imageArray = {R.drawable.guolai, R.drawable.guolai, R.drawable.guolai}; // 替换为您的图片资源
        String[] textArray = {"Column 1", "Column 2", "Column 3"}; // 栏目名
        String[] textArrayP = {"P 1", "P 2", "P 3"};
        String[] textArrayR = {"Recommend 1", "Recommend 2", "Recommend 3"};

        // 创建自定义适配器
        CustomAdapter adapter = new CustomAdapter(getActivity(), textArray, textArrayP, textArrayR, imageArray);
        listView.setAdapter(adapter);

        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        listView.setOnItemClickListener((adapterView, view1, position, l) -> {
            // 根据点击的位置跳转到相应的页面
            // 这里具体页面还没写，所以还没完善这里
            switch (position) {
                case 0:
                    // 跳转到column 1
                    //TODO：这里想把PageAcitivity换成AudioActivity，但是会闪退
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
                case 1:
                    // 跳转到column 2
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
                case 2:
                    // 跳转到column 3
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
            }
        });
        TextView cat_career = view.findViewById(R.id.category_career);
        cat_career.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });
        TextView cat_city = view.findViewById(R.id.category_city);
        cat_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });
        TextView cat_comedy = view.findViewById(R.id.category_comedy);
        cat_comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });
        TextView cat_cure = view.findViewById(R.id.category_cure);
        cat_cure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                startActivity(new Intent(getActivity(),CategoryActivity.class));
            }
        });
        TextView cat_finance = view.findViewById(R.id.category_finance);
        cat_finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });
        TextView cat_history = view.findViewById(R.id.category_history);
        cat_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });
        TextView cat_politic = view.findViewById(R.id.category_politic);
        cat_politic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });
        TextView cat_story = view.findViewById(R.id.category_story);
        cat_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });


        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        // 创建适配器，并向其中添加Fragment以用作页面
        YourPagerAdapter adapter = new YourPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Fragment1(), "最热专辑");
        adapter.addFragment(new Fragment2(), "最热单集");
        adapter.addFragment(new Fragment3(), "最新单集");
        viewPager.setAdapter(adapter);
    }


}



