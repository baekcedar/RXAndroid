package com.baekcedar.android.rxandroid_basic03_async;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DEFER ASYNC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doDeferAsync();
    }
    public void doDeferAsync(){
        Log.i(TAG, ":"+Thread.currentThread().getName()+": in Main");
        Observable<String> obv = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                Log.i(TAG, ":"+Thread.currentThread().getName()+": in Func0");
                return Observable.just("Here i am.");
            }
        });
        obv.subscribeOn(Schedulers.computation())   // 발행자 (Observable) Thread 를 지정
                .observeOn(Schedulers.newThread()) // 구독자 (Observer, subscriber) Thread 를 지정
                .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, ":"+Thread.currentThread().getName()+": in Subscriber / onCompleted");
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(String s) {
                Log.i(TAG, ":"+Thread.currentThread().getName()+": in Subscriber = "+s);
            }
        });
    }
}
