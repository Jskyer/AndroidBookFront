package com.example.newhelloworld.manager;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyActivityManager {
    private MyActivityManager(){}

    private static class ActivityManagerHolder{
        private static MyActivityManager instance = new MyActivityManager();
    }

    public static MyActivityManager getInstance(){
        return ActivityManagerHolder.instance;
    }

    private List<Activity> activities = new ArrayList<>();

    public void add(Activity activity){
        activities.add(activity);

        Log.d("MyActivityManager", "size: "+activities.size());
    }

    public void remove(Activity activity){
        activities.remove(activity);

        Log.d("MyActivityManager", "size: "+activities.size());
    }

    public void finishAll(){
        int n = activities.size();
        for(int i = 0; i < n; i++){
            Activity cur = activities.get(i);
            if(!cur.isFinishing())cur.finish();
        }
        activities.clear();

        Log.d("MyActivityManager", "size: "+activities.size());
    }
}
