package com.example.newhelloworld.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static class RetrofitHolder{
        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9090")
//                .baseUrl("http://39.101.66.253:9090")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    private RetrofitFactory(){}

    public static Retrofit getInstance(){
        return RetrofitHolder.retrofit;
    }
}
