package com.example.newhelloworld.net;

import com.example.newhelloworld.queryVO.LoginResp;
import com.example.newhelloworld.queryVO.SendVerificationResp;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MyRetrofitClient {

    public Observable<SendVerificationResp> verify(String email, Observer<SendVerificationResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<SendVerificationResp> res = iRequest.verify(email);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<SendVerificationResp> register(Map<String,String> map, Observer<SendVerificationResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<SendVerificationResp> res = iRequest.register(map);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }

    public Observable<LoginResp> login(Map<String,String> map, Observer<LoginResp> observer){
        Retrofit retrofit = RetrofitFactory.getInstance();
        IRequest iRequest = retrofit.create(IRequest.class);

        Observable<LoginResp> res = iRequest.login(map);
        res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return res;
    }
}
