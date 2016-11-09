package com.baekcedar.android.rxandroid_basic04_lambda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "TEST";
    ArrayList<String> datas = new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> adapter;
    TextView textView;
    Button btnLambda, btnMap, btnFlatmap, btnZip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView    = (TextView) findViewById(R.id.textView);
        btnLambda   = (Button) findViewById(R.id.btnLambda);
        btnMap      = (Button) findViewById(R.id.btnMap);
        btnFlatmap  = (Button) findViewById(R.id.btnFlatmap);
        btnZip      = (Button) findViewById(R.id.btnZip);
        listView    = (ListView) findViewById(R.id.listview);
        btnLambda.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnFlatmap.setOnClickListener(this);
        btnZip.setOnClickListener(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLambda:

                doLambda();
                break;
            case R.id.btnMap:
                doMap();
                break;
            case R.id.btnFlatmap:
                doFlatmap();
                break;
            case R.id.btnZip:
                doZip();
                break;

        }
    }


    public void doLambda(){
        Observable.just("i am Labmda")
        .subscribe(
            item -> textView.setText(item),
            Throwable::printStackTrace,
            () -> Log.i(TAG, "Complieted")
        );

    }
    public void doMap(){
        Observable.from(new String[]{"aaa","bbb","ccc","ddd","eee","fff"})
            .map( item-> "["+item+"]" )
            .subscribe(
                item -> datas.add(item),
                Throwable::printStackTrace,
                () -> adapter.notifyDataSetChanged()
        );
    }
    public void doFlatmap(){
        Observable.from(new String[]{"aaaa","bbbbbbb","cccccccc","ddd","ee","ffffffffff"})
            .flatMap(item -> Observable.from( new String[]{"name:"+item+" Byte: "+ item.getBytes().length}))
            .subscribe(
                item -> datas.add(item),
                Throwable::printStackTrace,
                () -> adapter.notifyDataSetChanged()
        );
    }
    public void doZip(){
        Observable.zip(Observable.just("BAEK HYANGMOK"),
                        Observable.just("image.jpg"),
                (item1,item2) -> "Name:"+item1 + ", Profile image:"+item2)
                .subscribe( zipped -> Log.w(TAG, "onNext item : " +zipped)
                );
    }
}