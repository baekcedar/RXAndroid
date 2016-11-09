package com.baekcedar.android.rxandroid_basic02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> datas = new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> adapter;
    TextView textView;
    Button btnDoJust, btnDoFrom, btnDoDefer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =(TextView) findViewById(R.id.textView);
        btnDoJust = (Button) findViewById(R.id.btnDoJust);
        btnDoFrom = (Button) findViewById(R.id.btnDoFrom);
        btnDoDefer = (Button) findViewById(R.id.btnDoDefer);
        listView = (ListView) findViewById(R.id.listview);


        adapter = new ArrayAdapter<String>(         // 인자 전달
                this,                               // 컨택스트
                android.R.layout.simple_list_item_1,// 아이템 레이아웃
                datas                               // 데이터
        );

        listView.setAdapter(adapter);

        btnDoJust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doJust();
            }
        });
        btnDoFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doFrom();
            }
        });
        btnDoDefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDefer();
            }
        });

    }

    public void doJust(){
        Log.i("test","doJust");
        Observable<String> obv = Observable.just("dog");

        obv.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText(s);
            }
        });
    }
    // 컬랙션 형태로 ....
    public void doFrom(){
        Log.i("test","doFrom");
        Observable<String> obv = Observable.from(new String[]{"dog","bird","chicken","apple"});

        obv.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                datas.add(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    // 지연처리 함수를 제공하고
    // 호출할때 마다 옵저버블 객체를 매번 생성한다.
    public void doDefer(){
        Log.i("test","doDefer");
        Observable<String>  obv = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("bird");
            }
        });

        obv.delaySubscription(3, TimeUnit.SECONDS)
            .subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    textView.setText(s);
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {

                }
            }, new Action0() {
                @Override
                public void call() {

                }
            });

    }
}
