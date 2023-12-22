package com.example.newhelloworld;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.activity.CategoryActivity;
import com.example.newhelloworld.adapter.SearchAdapter;
import com.example.newhelloworld.event.MsgToSearchResult;
import com.example.newhelloworld.manager.MyActivityManager;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.PodcastDo;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.userInfo.GetSearchResp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("SetTextI18n")
public class SearchResultActvity extends AppCompatActivity {
    private static final String TAG = "SearchResultActvity";
//    private TextView tv_search_result;

    private List<PodcastDo> podcasts;

    private Integer pageNum;
    private Integer pageSize;
    private String likeString;

    private RecyclerView rcycView;

    private SearchAdapter adapter;

    private MyRetrofitClient client;

    private Integer lastId = -1;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onSearchEventReceived(MsgToSearchResult msg){
        Log.d(TAG, "onSearchEventReceived");
        podcasts = msg.getPodcasts();
//        Log.d(TAG, ""+podcasts);

        if(podcasts != null && podcasts.size() > 0) {
            int id = podcasts.get(0).getPodcastId();
            if(id != lastId){
                lastId = id;
            }
        }

        pageNum = msg.getPageNum();
        pageSize = msg.getPageSize();
        likeString = msg.getLikeString();

        adapter.updateList(podcasts);

        Log.d(TAG, "pageNum: "+pageNum);
        EventBus.getDefault().removeStickyEvent(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        // 从布局文件中获取名叫tl_result的工具栏
        Toolbar tl_result = findViewById(R.id.tl_result);
        // 设置工具栏的背景
//        tl_result.setBackgroundResource(R.color.blue_light);
        // 设置工具栏的标志图片
//        tl_result.setLogo(R.mipmap.ic_launcher);
        // 设置工具栏的标题文字
//        tl_result.setTitle("搜索结果页");
        // 设置工具栏的导航图标
//        tl_result.setNavigationIcon(R.mipmap.ic_back);
        // 使用tl_result替换系统自带的ActionBar

        MyActivityManager.getInstance().add(this);

        setSupportActionBar(tl_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //添加默认返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回可用


        client = new MyRetrofitClient();
        podcasts = new ArrayList<>();

        bindView();
//        searchResult();
//        tv_search_result = findViewById(R.id.tv_search_result);
        // 执行搜索查询操作
//        doSearchQuery(getIntent());

        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // toolbar返回键的监听事件，id是系统默认
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void bindView(){
        rcycView = findViewById(R.id.search_list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcycView.setLayoutManager(manager);
        adapter = new SearchAdapter(podcasts, this);
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
                    pageNum++;
                    searchResult();
                }

            }
        });
    }

    public void searchResult(){
        Log.d(TAG, "pageNum: "+pageNum);
        client.searchPodcast(pageNum, pageSize, likeString, new MyObserver<GetSearchResp>() {
            @Override
            public void onSuccss(GetSearchResp getSearchResp) {
                Status status = getSearchResp.getStatus();
                if(status.getCode() == 200){
                    List<PodcastDo> podcasts = getSearchResp.getPodcasts();

                    if(podcasts != null && podcasts.size() > 0) {
                        int id = podcasts.get(0).getPodcastId();

                        if(id != lastId){
                            Log.d(TAG,"load new: " + status.getMsg());
                            adapter.updateList(podcasts);
                            lastId = id;
                        }else{
                            Toast.makeText(SearchResultActvity.this, "到底啦~", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else {
                    Log.d(TAG, "search error: "+status.getMsg());
                }
            }
        });
    }

    // 解析搜索请求页面传来的搜索信息，并据此执行搜索查询操作
//    private void doSearchQuery(Intent intent) {
//        if (intent != null) {
//            // 如果是通过ACTION_SEARCH来调用，即为搜索框来源
//            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//                // 获取额外的搜索数据
//                Bundle bundle = intent.getBundleExtra(SearchManager.APP_DATA);
//                String value = bundle.getString("hi");
//                // 获取实际的搜索文本
//                String queryString = intent.getStringExtra(SearchManager.QUERY);
//                tv_search_result.setText("您输入的搜索文字是："+queryString+", 额外信息："+value);
//            }
//        }
//    }

    public static void startAction(Context context){
        Intent intent = new Intent(context, SearchResultActvity.class);
        context.startActivity(intent);
    }

}
