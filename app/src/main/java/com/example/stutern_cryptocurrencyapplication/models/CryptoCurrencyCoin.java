package com.example.stutern_cryptocurrencyapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoCurrencyCoin implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("price_usd")
    @Expose
    private String priceUsd;
    @SerializedName("24h_volume_usd")
    @Expose
    private String _24hVolumeUsd;
    @SerializedName("market_cap_usd")
    @Expose
    private String marketCapUsd;
    @SerializedName("available_supply")
    @Expose
    private String availableSupply;
    @SerializedName("total_supply")
    @Expose
    private String totalSupply;
    @SerializedName("percent_change_1h")
    @Expose
    private String percentChange1h;
    @SerializedName("percent_change_24h")
    @Expose
    private String percentChange24h;

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public String get24hVolumeUsd() {
        return _24hVolumeUsd;
    }

    public String getMarketCapUsd() {
        return marketCapUsd;
    }

    public String getAvailableSupply() {
        return availableSupply;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(symbol);
        dest.writeString(priceUsd);
        dest.writeString(_24hVolumeUsd);
        dest.writeString(marketCapUsd);
        dest.writeString(availableSupply);
        dest.writeString(totalSupply);
        dest.writeString(percentChange1h);
        dest.writeString(percentChange24h);
    }

    public static final Parcelable.Creator<CryptoCurrencyCoin> CREATOR
        = new Parcelable.Creator<CryptoCurrencyCoin>() {
        @Override
        public CryptoCurrencyCoin createFromParcel(Parcel source) {
            return new CryptoCurrencyCoin(source);
        }

        @Override
        public CryptoCurrencyCoin[] newArray(int size) {
            return new CryptoCurrencyCoin[size];
        }
    };

    private CryptoCurrencyCoin(Parcel source) {
        name = source.readString();
        symbol = source.readString();
        priceUsd = source.readString();
        _24hVolumeUsd = source.readString();
        marketCapUsd = source.readString();
        availableSupply = source.readString();
        totalSupply = source.readString();
        percentChange1h = source.readString();
        percentChange24h = source.readString();
    }

    public boolean hasNegative24HourPercentageChange() {
        return percentChange24h.charAt(0) == '-';
    }
}