package com.baekcedar.android.rxandroid_bisic07_rxbinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Random;

import rx.Observable;

import static com.jakewharton.rxbinding.view.RxView.clicks;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "RX Binding";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clicks(findViewById(R.id.btnBind))
                .map(event -> new Random().nextInt())
                .subscribe(
                        rand -> ((TextView) findViewById(R.id.textView)).setText("value=" + rand)
                );

        Observable<String> leftObs = clicks(findViewById(R.id.btnLeft))
                .map(event -> "left");
        Observable<String> rightObs = clicks(findViewById(R.id.btnRight))
                .map(event -> "right");
        Observable.merge(leftObs, rightObs)
                .subscribe(
                        text -> Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                );

        // text Change Event
        RxTextView.textChangeEvents((TextView)findViewById(R.id.etSearch))
                .subscribe(
                    word -> Log.i(TAG, "search : "+word.text().toString())
                );

    }
}
