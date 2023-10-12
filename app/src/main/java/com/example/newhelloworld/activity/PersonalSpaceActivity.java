package com.example.newhelloworld.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newhelloworld.adapter.HistoryAdapter;
import com.example.newhelloworld.model.History;
import com.example.newhelloworld.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersonalSpaceActivity extends AppCompatActivity {

    private List<History> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);

//        setContentView(R.layout.history_layout);
//
//        initData();
//        RecyclerView rcycView = findViewById(R.id.personal_history_list);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        rcycView.setLayoutManager(manager);
//        HistoryAdapter adapter = new HistoryAdapter(historyList);
//        rcycView.setAdapter(adapter);
    }

    public void initData(){
        historyList = new ArrayList<>();
        for (int i = 1; i < 20; i++){
            History item = new History("啊啊啊啊啊啊",
                    "在 Java 中，集合是一个非常重要的概念，它是指一组相关对象的容器，在实际应用中使用十分广泛。Java 中提供了一个集合框架，它是指一组接口、实现类和算法，用于处理集合中的元素。",
                    i, LocalDateTime.now());
            historyList.add(item);
        }
    }
}
