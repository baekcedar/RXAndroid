package com.baekcedar.android.rxandroid_basic09_retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    static final String BASE_URL = "http://api.openweathermap.org";
    static final String API_KEY = "11c7f631cd74614447645fc773e2a1a7";
    EditText city;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        city = (EditText) findViewById(R.id.editText);
        Button btnWeather = (Button) findViewById(R.id.btnWeather);

        btnWeather.setOnClickListener( e -> getWeather() );

    }

    public void getWeather(){
        String cityName = city.getText().toString();

        // 1. Retrofit 클라이언트 생성
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        // 2. RestAPi 서비스 생성
        IWeather service = client.create(IWeather.class);

        // 3. 데이터 Observable 생성
        Observable<Data> weatherData = service.getData(cityName, API_KEY);

        // 4. scribeOn
        // 가. 데이터를 가져오는 대상 Observable : newTHread 로 새로운 Thread 에서 작업한다.
        // 나. 화면에 새팅하는 Observer : main Thread에서 작업한다.
        weatherData.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data -> {
                            String temp = "";
                            temp += "ID:" + data.getId();
                            temp += "\nName:" + data.getName();
                            temp += "\nBase:" + data.getBase();
                            result.setText(temp);
                    }
                );

    }
}
