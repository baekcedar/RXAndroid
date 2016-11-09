package com.baekcedar.android.rxandroid_basic06_subject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "RX SUBJECT ";

    Button btnPublish,btnBehavior,btnReplay,btnAsync,btnAsyncComplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPublish = (Button) findViewById(R.id.btnPublish);
        btnBehavior = (Button) findViewById(R.id.btnBehavior);
        btnReplay = (Button) findViewById(R.id.btnReplay);
        btnAsync = (Button) findViewById(R.id.btnAsync);
        btnAsyncComplete = (Button) findViewById(R.id.btnAsyncComplete);

        btnPublish.setOnClickListener(this);
        btnBehavior.setOnClickListener(this);
        btnReplay.setOnClickListener(this);
        btnAsync.setOnClickListener(this);
        btnAsyncComplete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPublish: publish(); break;
            case R.id.btnBehavior:behavior(); break;
            case R.id.btnReplay: replay(); break;
            case R.id.btnAsync: async(); break;
            case R.id.btnAsyncComplete: asyncComplete();break;
        }

    }
    public void publish(){
        PublishSubject<String> subject = PublishSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.onNext("D");
        subject.subscribe(
                item -> Log.e(TAG, "PublishSubject:"+item)
        );
        subject.onNext("E");
        subject.onNext("F");
        subject.onNext("G");

    }
    public void behavior(){
        //
        BehaviorSubject<String> subject = BehaviorSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.onNext("D");
        subject.subscribe(
                item -> Log.e(TAG, "BehaviorSubject:"+item)
        );
        subject.onNext("E");
        subject.onNext("F");
        subject.onNext("G");
    }
    public void replay(){
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.onNext("D");
        subject.subscribe(
                item -> Log.e(TAG, "ReplaySubject:"+item)
        );
        subject.onNext("E");
        subject.onNext("F");
        subject.onNext("G");
    }
    public void async(){
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.onNext("D");
        subject.subscribe(
                item -> Log.e(TAG, "AsyncSubject:"+item)
        );
        subject.onNext("E");
        subject.onNext("F");
        subject.onNext("G");
    }
    public void asyncComplete(){
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.onNext("D");
        subject.subscribe(
                item -> Log.e(TAG, "AsyncComplete:"+item)
        );
        subject.onNext("E");
        subject.onNext("F");
        subject.onNext("G");
        subject.onCompleted(); // onCompleted 를 써줘야 됨.( 끝이 어딘지 알때 사용 )
    }

}
