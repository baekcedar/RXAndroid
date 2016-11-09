package com.baekcedar.android.rxandroid_basic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = " RX TEST : ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. Observable 생성
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RX Android !");
//                subscriber.onNext("HAHAHAHAHA");
                subscriber.onCompleted();
            }
        });
        // 2. Observable 을 통해 데이터를 가져온다
        observable.subscribe(new Subscriber<String>() {
                                // 옵저버 등록
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(String s) {
                Log.i(TAG, ""+s);
            }
        });

        observable.subscribe(new Action1<String>() {    // OnNext
            @Override
            public void call(String s) {
                ((TextView) findViewById(R.id.textView)).setText(s);
            }
        }, new Action1<Throwable>() {   //onError

            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {  // OnCompleted
            @Override
            public void call() {

            }
        });
    }
}
