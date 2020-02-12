package com.example.stutern_cryptocurrencyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.stutern_cryptocurrencyapplication.models.CryptoCurrencyCoin;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CryptoCurrencyClient client = CryptoCurrencyClient.getCryptoCurrencyClient();

        Subscription subscription = client.getCryptoCurrencyCoins(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<ArrayList<CryptoCurrencyCoin>>() {
                            @Override
                            public void onCompleted() {
                                Log.d(LOG_TAG, "In onCompleted");
                            }
                            @Override
                            public void onError(Throwable e) {
                                Log.e(LOG_TAG, e.getMessage());
                            }

                            @Override
                            public void onNext(
                                    ArrayList<CryptoCurrencyCoin> coins) {
                                Log.d(LOG_TAG, coins.get(0).getName());
                            }
                        }

                );
    }
}
