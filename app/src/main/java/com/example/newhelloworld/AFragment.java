package com.example.newhelloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.newhelloworld.activity.AudioActivity;
import com.example.newhelloworld.activity.CategoryActivity;
import com.example.newhelloworld.activity.PageActivity;
import com.example.newhelloworld.adapter.CustomAdapter;
import com.example.newhelloworld.adapter.YourPagerAdapter;
import com.example.newhelloworld.event.MsgAddToAudioList;
import com.example.newhelloworld.event.MsgToCategory;
import com.example.newhelloworld.event.MsgToSearchResult;
import com.example.newhelloworld.manager.AudioListManager;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.Album;
import com.example.newhelloworld.pojo.Podcast;
import com.example.newhelloworld.pojo.PodcastDo;
import com.example.newhelloworld.pojo.PodcastOffiRec;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.album.GetPopularAlbumResp;
import com.example.newhelloworld.queryVO.podcast.GetPodcastOffiRecResp;
import com.example.newhelloworld.queryVO.podcast.GetPodcastResp;
import com.example.newhelloworld.queryVO.userInfo.GetSearchResp;
import com.example.newhelloworld.util.ModelUtil;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AFragment extends Fragment {
    public static final String TAG = "AFragment";

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private View view;
    private ListView listView;
    private CustomAdapter adapter;
    private MyRetrofitClient client;

    private List<Podcast> podcasts;

    private String[] imageArray;
    private  String[] textArray; // 栏目名
    private  String[] textArrayP;
    private  String[] textArrayR;

    private AudioListManager instance;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        client = new MyRetrofitClient();

        view = inflater.inflate(R.layout.fragment_a, container, false);
        listView = view.findViewById(R.id.list_view);
        podcasts = new ArrayList<>();

        // 创建图像数组和文本数组
//        Integer[] imageArray = {R.drawable.guolai, R.drawable.guolai, R.drawable.guolai}; // 替换为您的图片资源
//        String[] textArray = {"Column 1", "Column 2", "Column 3"}; // 栏目名
//        String[] textArrayP = {"P 1", "P 2", "P 3"};
//        String[] textArrayR = {"Recommend 1", "Recommend 2", "Recommend 3"};
        imageArray = new String[3];
        textArray = new String[3];
        textArrayP = new String[3];
        textArrayR = new String[3];

        requestTopList();

        // 创建自定义适配器
//        CustomAdapter adapter = new CustomAdapter(getActivity(), textArray, textArrayP, textArrayR, imageArray);
//        listView.setAdapter(adapter);

        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        instance = AudioListManager.getInstance();

        listView.setOnItemClickListener((adapterView, view1, position, l) -> {
            // 根据点击的位置跳转到相应的页面
            // 这里具体页面还没写，所以还没完善这里
            switch (position) {
                case 0:
//                    Episode episode1 = new Episode(1, "title1", "user_name1", 237, null, "/music/obj168925.mp3");
//                    Episode episode1 = new Episode(1, "title1", "user_name1", 237, null, "/song/song1.mp3");

                    Episode episode1 = ModelUtil.transEpisode(podcasts.get(0));

                    instance.addData(episode1);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode1));

                    // 跳转到column 1
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
                case 1:
//                    Episode episode2 = new Episode(2, "title2", "user_name2", 210, null, "/song/song2.mp3");

                    Episode episode2 = ModelUtil.transEpisode(podcasts.get(1));
                    instance.addData(episode2);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode2));

                    // 跳转到column 2
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
                case 2:
//                    Episode episode3 = new Episode(3, "title3", "user_name3", 265, null, "/song/song3.mp3");

                    Episode episode3 = ModelUtil.transEpisode(podcasts.get(2));
                    instance.addData(episode3);
                    EventBus.getDefault().postSticky(new MsgAddToAudioList(episode3));
                    // 跳转到column 3
                    startActivity(new Intent(getActivity(), AudioActivity.class));
                    break;
            }
        });

        setSearchConfig();

        setFirstRow();

        setSecondRow();

        return view;
    }

    public void setSearchConfig(){
        SearchView searchView = view.findViewById(R.id.searchView);

        //输入和提交监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "onQueryTextSubmit");
                Log.d(TAG, ""+s);

                client.searchPodcast(1, 8, s, new MyObserver<GetSearchResp>() {
                    @Override
                    public void onSuccss(GetSearchResp getSearchResp) {
                        Status status = getSearchResp.getStatus();
                        if(status.getCode() == 200){
                            //TODO 跳转result
                            List<PodcastDo> podcasts = getSearchResp.getPodcasts();

                            EventBus.getDefault().postSticky(new MsgToSearchResult(podcasts));
                            SearchResultActvity.startAction(getActivity());
                        }else {
                            Log.d(TAG, "search error: "+status.getMsg());
                        }
                    }
                });


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "onQueryTextChange");
                return false;
            }
        });

        //关闭事件
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.d(TAG, "onClose");
                return false;
            }
        });
    }

    public void setFirstRow(){
        TextView cat_history = view.findViewById(R.id.category_history);
        cat_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                EventBus.getDefault().postSticky(new MsgToCategory("pop"));

                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });


        TextView cat_career = view.findViewById(R.id.category_career);
        cat_career.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                EventBus.getDefault().postSticky(new MsgToCategory("rock"));
                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });

        TextView cat_story = view.findViewById(R.id.category_story);
        cat_story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                EventBus.getDefault().postSticky(new MsgToCategory("folk"));

                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });

        TextView cat_city = view.findViewById(R.id.category_city);
        cat_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑

                EventBus.getDefault().postSticky(new MsgToCategory("jazz"));

                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });
    }


    public void setSecondRow(){
        TextView cat_politic = view.findViewById(R.id.category_politic);
        cat_politic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                EventBus.getDefault().postSticky(new MsgToCategory("absolute music"));

                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });

        TextView cat_finance = view.findViewById(R.id.category_finance);
        cat_finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                EventBus.getDefault().postSticky(new MsgToCategory("rap"));

                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });

        TextView cat_cure = view.findViewById(R.id.category_cure);
        cat_cure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                EventBus.getDefault().postSticky(new MsgToCategory("classical"));

                startActivity(new Intent(getActivity(),CategoryActivity.class));
            }
        });

        TextView cat_comedy = view.findViewById(R.id.category_comedy);
        cat_comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里实现页面跳转逻辑
                EventBus.getDefault().postSticky(new MsgToCategory("chinese style"));

                startActivity(new Intent(getActivity(), CategoryActivity.class));
            }
        });
    }



    public void requestTopList(){
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
                        textArrayR[i] = podcasts.get(i).getCreateTime();
//                        textArrayR[i] = podcasts.get(i).getViews().toString();
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




    private void setupViewPager(ViewPager viewPager) {
        // 创建适配器，并向其中添加Fragment以用作页面
        YourPagerAdapter adapter = new YourPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Fragment1(), "最热专辑");
        adapter.addFragment(new Fragment2(), "最热单集");
        adapter.addFragment(new Fragment3(), "最新单集");
        viewPager.setAdapter(adapter);
    }


}



