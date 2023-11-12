package com.example.newhelloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.newhelloworld.activity.AudioActivity;

public class Fragment2 extends Fragment {
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.afragment_2, container, false);

        listView = view.findViewById(R.id.listView);

        // 创建一个包含栏目名称的数组
        String[] columns = {"column 1", "column 2", "column 3"};

        // 使用ArrayAdapter设置ListView的内容
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, columns);
        listView.setAdapter(adapter);

        // 设置ListView的点击事件
        listView.setOnItemClickListener((adapterView, view1, position, l) -> {
            // 根据点击的位置跳转到相应的页面
            // 这里具体页面还没写，所以还没完善这里
            switch (position) {
                case 0:
                    // 跳转到column 1
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


        return view;
    }
}
