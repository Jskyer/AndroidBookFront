package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.R;
import com.example.newhelloworld.adapter.HistoryAdapter;
import com.example.newhelloworld.databinding.HistoryLayoutBinding;
import com.example.newhelloworld.model.Episode;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.HistoryInfo;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.userInfo.GetHistoryResp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends ViewBindingActivity<HistoryLayoutBinding> {
    public static final String TAG = "HistoryActivity";

    private List<HistoryInfo> episodeList;

    private RecyclerView rcycView;

    private HistoryAdapter adapter;

    private MyRetrofitClient client;

    private int pageNo = 1;

    private int pageSize = 5;

    private int lastHistoryId = -1;

    public static void startAction(Context context){
        Intent intent = new Intent(context, HistoryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.history_layout);

//        prepareClient();
        bindView();
        loadData();
    }


//    @Override
//    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onPostCreate(savedInstanceState, persistentState);
//
//        loadData();
//        Log.d(TAG, "onPostCreate");
//    }

    public void bindView(){
        client = new MyRetrofitClient();
        episodeList = new ArrayList<>();

        rcycView = findViewById(R.id.personal_history_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);
        adapter = new HistoryAdapter(episodeList, this);
        rcycView.setAdapter(adapter);

        rcycView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                int itemCount = adapter.getItemCount();
                int last = layoutManager.findLastVisibleItemPosition();
                int childCount = recyclerView.getChildCount();

                if(newState == RecyclerView.SCROLL_STATE_IDLE
                        && last == itemCount - 1
                        && childCount > 0){
                    Log.d(TAG, "加载下一页");
                    pageNo++;
                    loadData();
                }

            }
        });
    }


    public void prepareClient(){

//        for (int i = 1; i < 20; i++){
//            Episode item = new Episode(i,"啊啊啊啊啊啊",
//                    "user111",200,
//                    LocalDateTime.now(), null);
//            episodeList.add(item);
//        }
    }


    public void loadData(){
        client.getMorePodcastPreviews(pageNo, pageSize, new MyObserver<GetHistoryResp>() {
            @Override
            public void onSuccss(GetHistoryResp getHistoryResp) {
                Status status = getHistoryResp.getStatus();

                if(status.getCode() == 200){
//                    episodeList.addAll(getHistoryResp.getHistorys());

                    List<HistoryInfo> historys = getHistoryResp.getHistorys();
                    if(historys != null && historys.size() > 0){
                        Integer history_id = historys.get(0).getHistory_id();
                        if(history_id != lastHistoryId){
                            Log.d(TAG,"load new: " + status.getMsg());
                            adapter.updateList(historys);
                            lastHistoryId = history_id;
                        }else{
                            Toast.makeText(HistoryActivity.this, "到底啦~", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else{
                    Log.d(TAG,"status error: " + status.getMsg());
                }

            }
        });
    }

}
