package com.example.newhelloworld.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newhelloworld.R;
import com.example.newhelloworld.net.MyObserver;
import com.example.newhelloworld.net.MyRetrofitClient;
import com.example.newhelloworld.pojo.SubscribeInfo;
import com.example.newhelloworld.queryVO.Status;
import com.example.newhelloworld.queryVO.userInfo.GetSubscribeResp;
import com.example.newhelloworld.util.WaterfallUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SubscribeActicity extends AppCompatActivity {
    public static final String TAG = "SubscribeActivity";
    private int pageNo = 1;

    private int pageSize = 6;
    MyRetrofitClient client;
    List<String> imageFilePaths;
    List<SubscribeInfo> subscribeInfos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_acticity);
        //WaterfallUtil WaterfallUtil = (WaterfallUtil) findViewById(R.id.waterfall);
        imageFilePaths=new ArrayList<>();
        client=new MyRetrofitClient();
        init();
        //WaterfallUtil.setup(imageFilePaths);

        FloatingActionButton fab=findViewById(R.id.back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });


    }
    private void init(){
        //TODO:ALBUM后端修改后测试
/*        client.getSubscribePreviews(pageNo, pageSize, new MyObserver<GetSubscribeResp>() {
            @Override
            public void onSuccss(GetSubscribeResp getSubscribeResp) {
                Status status=getSubscribeResp.getStatus();
                if(status.getCode()==200){
                    subscribeInfos=getSubscribeResp.getSubscribes();
                    for (int i=0;i<subscribeInfos.size();i++){
                        String path=removePrefix(subscribeInfos.get(i).getPodcast_poster(),"/album/");
                        imageFilePaths.add(path);
                        Log.d(TAG,"加入关注列表"+path);
                    }
                    WaterfallUtil WaterfallUtil = (WaterfallUtil) findViewById(R.id.waterfall);
                    WaterfallUtil.setup(imageFilePaths);

                }
            }
        });*/
        String path=removePrefix("/album/1702195552966_song001.png","/album/");
        imageFilePaths.add(path);
        WaterfallUtil WaterfallUtil = (WaterfallUtil) findViewById(R.id.waterfall);
        WaterfallUtil.setup(imageFilePaths);

    }

    public static String removePrefix(String str, String pf) {
        if (StringUtils.isEmpty(str)) {
            return "";
        } else {
            if (null != pf) {

                    if (str.toLowerCase().matches("^" + pf.toLowerCase() + ".*")) {
                        return str.substring(pf.length());//截取前缀后面的字符串
                    }
                }
            }

            return str;
        }



}

