package com.example.newhelloworld.net;

import android.util.Log;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class MyObserver<T> implements Observer<T> {

    public abstract void onSuccss(T t);

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        Log.d("rxjava", t+"");
        onSuccss(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.d("rxjava", "error: " + e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.d("rxjava", "onComplete success");
    }
}
