package com.example.newhelloworld;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.newhelloworld.greenDao.DaoMaster;
import com.example.newhelloworld.greenDao.DaoSession;

/**
 * 管理全局的context
 */
public class MyApplication extends Application {

    private static Context context;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "podoasis.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }


    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 获取全局上下文*/
    public static Context getContext() {
        return context;
    }
}
