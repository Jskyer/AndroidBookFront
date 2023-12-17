package com.example.newhelloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.newhelloworld.activity.AudioActivity;
import com.example.newhelloworld.activity.PageActivity;
import com.example.newhelloworld.activity.SubscribeActicity;
import com.example.newhelloworld.adapter.CustomAdapter;
import com.example.newhelloworld.event.MsgAddToAudioList;
import com.example.newhelloworld.manager.AudioListManager;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.Podcast;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.podcast.GetPodcastOffiRecResp;
import com.example.newhelloworld.queryVO.podcast.GetPodcastRankPreviewResp;
import com.example.newhelloworld.util.ModelUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class BFragment extends Fragment {

    public static final String TAG="BFragment";
    private String[] imageArray;
    private  String[] textArray; // 栏目名
    private  String[] textArrayP;
    private  String[] textArrayR;
    private AudioListManager instance;
    private View view;
    private ListView listView;
    private CustomAdapter adapter;
    private MyRetrofitClient client;

    private List<Podcast> podcasts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
/*        View view = inflater.inflate(R.layout.fragment_b, container, false);
        ListView listView = view.findViewById(R.id.list_view);*/
        client=new MyRetrofitClient();
        view=inflater.inflate(R.layout.fragment_b, container, false);
        listView = view.findViewById(R.id.list_view);
        podcasts=new ArrayList<>();
        imageArray = new String[3];
        textArray = new String[3];
        textArrayP = new String[3];
        textArrayR = new String[3];
        requestLatestSubscribe();
        instance=AudioListManager.getInstance();
/*        CustomAdapter adapter = new CustomAdapter(getActivity(), textArray, textArrayP, textArrayR, imageArray);
        listView.setAdapter(adapter);*/
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
                    Episode episode1= ModelUtil.transEpisode(podcasts.get(0));
                    instance.addData(episode1);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode1));
                    // 跳转到column 1
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
                case 1:
                    Episode episode2= ModelUtil.transEpisode(podcasts.get(1));
                    instance.addData(episode2);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode2));
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
                case 2:
                    Episode episode3= ModelUtil.transEpisode(podcasts.get(2));
                    instance.addData(episode3);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode3));
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
                case 3:
                    Episode episode4= ModelUtil.transEpisode(podcasts.get(3));
                    instance.addData(episode4);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode4));
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
                case 4:
                    Episode episode5= ModelUtil.transEpisode(podcasts.get(4));
                    instance.addData(episode5);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode5));
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
                case 5:
                    Episode episode6= ModelUtil.transEpisode(podcasts.get(5));
                    instance.addData(episode6);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode6));
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
            }
        });

        return view;
    }

    private void requestLatestSubscribe() {
        client.getPodcastOffiRec(new MyObserver<GetPodcastOffiRecResp>() {
            @Override
            public void onSuccss(GetPodcastOffiRecResp getPodcastOffiRecResp) {
                Status status = getPodcastOffiRecResp.getStatus();
                if(status.getCode() == 200){
                    podcasts = getPodcastOffiRecResp.getPodcasts();
                    for(int i = 0; i < 3; i++){
                        imageArray[i] = podcasts.get(i).getPodcastPoster();
                        textArray[i] = podcasts.get(i).getTitle();
                        textArrayP[i] = podcasts.get(i).getUploaderName();
                        textArrayR[i] = podcasts.get(i).getViews().toString();
                    }

                    adapter = new CustomAdapter(getActivity(), textArray, textArrayP, textArrayR, imageArray);
                    listView.setAdapter(adapter);

                    Log.d(TAG, "getPodcastOffiRec ok");
                }else{
                    Log.d(TAG, "getPodcastOffiRec error");
                }

            }
        });
    }


}




