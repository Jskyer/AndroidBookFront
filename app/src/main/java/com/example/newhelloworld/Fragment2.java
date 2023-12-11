package com.example.newhelloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.newhelloworld.activity.AudioActivity;
import com.example.newhelloworld.adapter.CustomAdapter;
import com.example.newhelloworld.event.MsgAddToAudioList;
import com.example.newhelloworld.manager.AudioListManager;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.Podcast;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.podcast.GetPodcastOffiRecResp;
import com.example.newhelloworld.util.ModelUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class Fragment2 extends Fragment {
    public static final String TAG = "Fragment2";

    private View view;
    private ListView listView;

    private MyRetrofitClient client;

    private List<Podcast> podcasts;

    private ArrayAdapter<String> adapter;

    private AudioListManager instance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.afragment_2, container, false);
        listView = view.findViewById(R.id.listView);

        client = new MyRetrofitClient();

        // 创建一个包含栏目名称的数组
//        String[] columns = {"column 1", "column 2", "column 3"};
//        // 使用ArrayAdapter设置ListView的内容
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, columns);
//        listView.setAdapter(adapter);

        requestHotList();

        instance = AudioListManager.getInstance();

        //TODO
        // 设置ListView的点击事件
        listView.setOnItemClickListener((adapterView, view1, position, l) -> {
            // 根据点击的位置跳转到相应的页面
            // 这里具体页面还没写，所以还没完善这里
            switch (position) {
                case 0:
                    // 跳转到column 1
                    Episode episode1 = ModelUtil.transEpisode(podcasts.get(0));
                    instance.addData(episode1);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode1));
                    startActivity(new Intent(getActivity(), AudioActivity.class));

                    break;
                case 1:
                    // 跳转到column 2
                    Episode episode2 = ModelUtil.transEpisode(podcasts.get(1));
                    instance.addData(episode2);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode2));
                    startActivity(new Intent(getActivity(), AudioActivity.class));

                    break;
                case 2:
                    // 跳转到column 3
                    Episode episode3 = ModelUtil.transEpisode(podcasts.get(2));
                    instance.addData(episode3);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode3));
                    startActivity(new Intent(getActivity(), AudioActivity.class));

                    break;
            }
        });


        return view;
    }


    public void requestHotList(){
        client.getPodcastRankPreviewView(new MyObserver<GetPodcastOffiRecResp>() {
            @Override
            public void onSuccss(GetPodcastOffiRecResp getPodcastOffiRecResp) {
                Status status = getPodcastOffiRecResp.getStatus();
                if(status.getCode() == 200){
                    podcasts = getPodcastOffiRecResp.getPodcasts();

                    String[] strings = new String[3];
                    for(int i = 0; i < 3; i++) {
                        strings[i] = podcasts.get(i).getTitle();
                    }
                    adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strings);
                    listView.setAdapter(adapter);

                    Log.d(TAG, "requestHotList ok");
                }else{
                    Log.d(TAG, "requestHotList error");
                }

            }
        });

    }
}
