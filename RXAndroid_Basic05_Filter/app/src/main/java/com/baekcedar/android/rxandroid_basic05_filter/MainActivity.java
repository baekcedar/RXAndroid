package com.baekcedar.android.rxandroid_basic05_filter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Integer dataSet[] = { 1,2,3,1,4,5,3,6,7 };
    Button btnFilter, btnTake, btnDistinct,btnFirst,btnForeach,btnGroup,btnLast;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDistinct = (Button) findViewById(R.id.btnDistinct);
        btnFilter = (Button) findViewById(R.id.btnFilter);
        btnFirst = (Button) findViewById(R.id.btnFirst);
        btnForeach = (Button) findViewById(R.id.btnForeach);
        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnTake = (Button) findViewById(R.id.btnTake);
        btnLast = (Button) findViewById(R.id.btnLast);
        tv = (TextView) findViewById(R.id.textView);

        btnDistinct.setOnClickListener(this);
        btnFilter.setOnClickListener(this);
        btnFirst.setOnClickListener(this);
        btnForeach.setOnClickListener(this);
        btnGroup.setOnClickListener(this);
        btnTake.setOnClickListener(this);
        btnLast.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnDistinct: Distinct(); break;
            case R.id.btnFilter: filter(); break;
            case R.id.btnFirst: First(); break;
            case R.id.btnForeach: Foreach(); break;
            case R.id.btnGroup: Group(); break;
            case R.id.btnTake: Take(3); break;
            case R.id.btnLast: Last(); break;

        }
    }

    public void Distinct(){
        StringBuffer bf = new StringBuffer();
        Observable.from(dataSet)
                .distinct()
                .subscribe(result -> bf.append(result)
        );
        tv.setText(bf.toString());
    }
    public void filter(){
        StringBuffer bf = new StringBuffer();
        Observable.from(dataSet)
                .filter(item -> item%2==0)
                .subscribe(
                        result -> bf.append(result)
                );
        tv.setText(bf.toString());
    }

    public void Foreach(){
        StringBuffer bf = new StringBuffer();
        Observable.from(dataSet)
                .forEach(result -> bf.append(result) );

        tv.setText(bf.toString());
    }
    public void Group(){
        StringBuffer bf = new StringBuffer();
        Observable.from(dataSet)
                .groupBy(item -> item%2==0)
                .subscribe(
                        grouped ->  grouped.toList().subscribe(item-> bf.append(item+""+grouped.getKey()))

                );
        tv.setText(bf.toString());
    }
    public void Take(int count){
        StringBuffer bf = new StringBuffer();
        Observable.from(dataSet)
                .take(count)
                .subscribe( result -> bf.append(result)
                );
        tv.setText(bf.toString());
    }
    public void First(){
        StringBuffer bf = new StringBuffer();
        Observable.from(dataSet)
                .first()
                .subscribe(result -> bf.append(result)
                );
        tv.setText(bf.toString());
    }
    public void Last(){
        StringBuffer bf = new StringBuffer();
        Observable.from(dataSet)
                .last()
                .subscribe(result -> bf.append(result)
                );
        tv.setText(bf.toString());
    }
}
