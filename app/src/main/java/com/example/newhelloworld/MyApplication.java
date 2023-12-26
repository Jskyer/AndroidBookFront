package com.example.newhelloworld;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;

import com.example.newhelloworld.greenDao.DaoMaster;
import com.example.newhelloworld.greenDao.DaoSession;

import java.lang.reflect.Field;

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
        replaceSystemDefaultFont(this);
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
    public void replaceSystemDefaultFont(Context context) {

        replaceTypefaceField("MONOSPACE", createTypeface(context));
    }

    //通过字体地址创建自定义字体
    private Typeface createTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "cat.ttf");
    }

    //关键--》通过修改MONOSPACE字体为自定义的字体达到修改app默认字体的目的
    private void replaceTypefaceField(String fieldName, Object value) {
        try {
            Field defaultField = Typeface.class.getDeclaredField(fieldName);
            defaultField.setAccessible(true);
            defaultField.set(null, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
