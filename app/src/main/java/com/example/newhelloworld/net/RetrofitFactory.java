package com.example.newhelloworld.net;

import androidx.annotation.NonNull;

import com.example.newhelloworld.MyApplication;
import com.example.newhelloworld.util.PreferenceUtil;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static class RetrofitHolder{
        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9090")
//                .baseUrl("http://10.0.2.2:7070")
//                .baseUrl("http://139.224.194.55:9090")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    private RetrofitFactory(){}

    public static Retrofit getInstance(){
        return RetrofitHolder.retrofit;
    }

    private static OkHttpClient getClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Cache cache = new Cache(MyApplication.getContext().getCacheDir(), 1024*1024*100);
        builder.cache(cache);
        builder.addNetworkInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                String token = PreferenceUtil.token(MyApplication.getContext());
                if(token != null){
//                    request = request.newBuilder().addHeader("token", token).build();
                    request = request.newBuilder().addHeader("Authorization", "Bearer " + token).build();
                }
                return chain.proceed(request);
            }
        });
        return builder.build();
    }
}
