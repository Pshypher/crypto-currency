package com.example.stutern_cryptocurrencyapplication;

import com.example.stutern_cryptocurrencyapplication.models.CryptoCurrencyCoin;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface CryptoCurrencyService {

    @GET("/v1/ticker")
    Observable<ArrayList<CryptoCurrencyCoin>> getCryptoCurrencyCoins(@Query("limit") int value);

}
