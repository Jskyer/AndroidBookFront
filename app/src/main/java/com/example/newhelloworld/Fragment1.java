package com.example.newhelloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.newhelloworld.activity.AudioActivity;
import com.example.newhelloworld.activity.PageActivity;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.Album;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.album.GetPopularAlbumResp;

import java.util.List;

public class Fragment1 extends Fragment {
    public static final String TAG = "Fragment1";
    private View view;
    private ListView listView;

    private MyRetrofitClient client;

    private List<Album> albums;

    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.afragment_1, container, false);

        listView = view.findViewById(R.id.listView);

        client = new MyRetrofitClient();

        // 创建一个包含栏目名称的数组
//        String[] columns = {"column 1", "column 2", "column 3"};
//        // 使用ArrayAdapter设置ListView的内容
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, columns);
//        listView.setAdapter(adapter);

        getPopularAlbum();

        //TODO
        // 设置ListView的点击事件
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
            }
        });

        return view;
    }

    public void getPopularAlbum(){
        client.getPopularAlbum(1, 3, new MyObserver<GetPopularAlbumResp>() {
            @Override
            public void onSuccss(GetPopularAlbumResp getPopularAlbumResp) {
                Status status = getPopularAlbumResp.getStatus();
                if(status.getCode() == 200){
                    albums = getPopularAlbumResp.getAlbums();
//                    adapter = new AlbumAdapter(getActivity(), albums);
                    String[] strings = new String[3];
                    for(int i = 0; i < 3; i++) {
                        strings[i] = albums.get(i).getAlbum_name();
                    }

                    adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strings);
                    listView.setAdapter(adapter);

                    Log.d(TAG, "getPopularAlbum ok");
                }else {
                    Log.d(TAG, "getPopularAlbum error");
                }
            }
        });
    }
}
