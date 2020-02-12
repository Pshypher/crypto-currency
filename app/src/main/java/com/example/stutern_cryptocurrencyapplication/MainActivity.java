package com.example.stutern_cryptocurrencyapplication;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.example.stutern_cryptocurrencyapplication.models.CryptoCurrencyCoin;

public class MainActivity extends AppCompatActivity {

    private CryptoCurrencyAdapter mAdapter;
    private Subscription mSubscription;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int VERTICAL_ITEM_SPACE = 16;

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylcer_view);
        recyclerView.addItemDecoration(new VerticalSpacingItemDecoration(VERTICAL_ITEM_SPACE));
        RecyclerView.LayoutManager manager =
                new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        CryptoCurrencyClient client = CryptoCurrencyClient.getCryptoCurrencyClient();

        mSubscription = client.getCryptoCurrencyCoins(50)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<ArrayList<CryptoCurrencyCoin>>() {
                            @Override
                            public void onCompleted() {
                                recyclerView.setAdapter(mAdapter);
                            }
                            @Override
                            public void onError(Throwable e) {
                                Log.e(LOG_TAG, e.getMessage());
                            }

                            @Override
                            public void onNext(
                                    ArrayList<CryptoCurrencyCoin> coins) {
                                mAdapter = new CryptoCurrencyAdapter(coins);
                            }
                        }
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }
}
