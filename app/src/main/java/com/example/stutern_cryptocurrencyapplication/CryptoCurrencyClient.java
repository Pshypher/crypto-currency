package com.example.stutern_cryptocurrencyapplication;

import com.example.stutern_cryptocurrencyapplication.models.CryptoCurrencyCoin;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class CryptoCurrencyClient {

    private CryptoCurrencyService mCryptoCurrencyService;

    private static CryptoCurrencyClient mCryptoCurrencyClient;
    private static final String BASE_URL = "https://api.coinmarketcap.com";


    private CryptoCurrencyClient() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(
                                                                                    Schedulers.io());

        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .addCallAdapterFactory(rxAdapter)
                                        .build();

        mCryptoCurrencyService = retrofit.create(CryptoCurrencyService.class);
    }

    public static CryptoCurrencyClient getCryptoCurrencyClient() {
        if (mCryptoCurrencyClient == null) {
            mCryptoCurrencyClient = new CryptoCurrencyClient();
        }

        return mCryptoCurrencyClient;
    }

    public Observable<ArrayList<CryptoCurrencyCoin>> getCryptoCurrencyCoins(int value) {
        return mCryptoCurrencyService.getCryptoCurrencyCoins(value);
    }
}
