package com.baekcedar.android.rxandroid_basic08_rxbindinglogincheck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.Observable;

public class MainActivity extends AppCompatActivity {
// 11c7f631cd74614447645fc773e2a1a7
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSign = (Button) findViewById(R.id.btnSign);
        btnSign.setEnabled(false);
        Observable<TextViewTextChangeEvent> idObs
                =  RxTextView.textChangeEvents((EditText) findViewById(R.id.etID));
        Observable<TextViewTextChangeEvent> passObs
                =  RxTextView.textChangeEvents((EditText) findViewById(R.id.etPass));

        Observable.combineLatest(idObs,passObs,
                (idChanges,passChanges) -> {
                    boolean idCheck = idChanges.text().length() >= 5;
                    boolean passCheck = passChanges.text().length() >= 8;
                    return idCheck && passCheck;
                })
                .subscribe(
                checkFlag -> btnSign.setEnabled(checkFlag)
        );

    }
}
