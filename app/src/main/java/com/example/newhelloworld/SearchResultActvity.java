package com.example.newhelloworld;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;

import com.example.newhelloworld.activity.HistoryActivity;

@SuppressLint("SetTextI18n")
public class SearchResultActvity extends AppCompatActivity {
    private static final String TAG = "SearchResultActvity";
    private TextView tv_search_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        // 从布局文件中获取名叫tl_result的工具栏
        Toolbar tl_result = findViewById(R.id.tl_result);
        // 设置工具栏的背景
        tl_result.setBackgroundResource(R.color.blue_light);
        // 设置工具栏的标志图片
        tl_result.setLogo(R.mipmap.ic_launcher);
        // 设置工具栏的标题文字
        tl_result.setTitle("搜索结果页");
        // 设置工具栏的导航图标
        tl_result.setNavigationIcon(R.mipmap.ic_back);
        // 使用tl_result替换系统自带的ActionBar
        setSupportActionBar(tl_result);
        tv_search_result = findViewById(R.id.tv_search_result);
        // 执行搜索查询操作
        doSearchQuery(getIntent());
    }

    // 解析搜索请求页面传来的搜索信息，并据此执行搜索查询操作
    private void doSearchQuery(Intent intent) {
        if (intent != null) {
            // 如果是通过ACTION_SEARCH来调用，即为搜索框来源
            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                // 获取额外的搜索数据
                Bundle bundle = intent.getBundleExtra(SearchManager.APP_DATA);
                String value = bundle.getString("hi");
                // 获取实际的搜索文本
                String queryString = intent.getStringExtra(SearchManager.QUERY);
                tv_search_result.setText("您输入的搜索文字是："+queryString+", 额外信息："+value);
            }
        }
    }

    public static void startAction(Context context){
        Intent intent = new Intent(context, SearchResultActvity.class);
        context.startActivity(intent);
    }

}
