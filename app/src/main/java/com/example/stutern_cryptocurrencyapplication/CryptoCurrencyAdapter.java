package com.example.stutern_cryptocurrencyapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.stutern_cryptocurrencyapplication.models.CryptoCurrencyCoin;

import java.util.ArrayList;

public class CryptoCurrencyAdapter extends RecyclerView.Adapter<CryptoCurrencyAdapter.ViewHolder> {

    private ArrayList<CryptoCurrencyCoin> mCoins;

    public CryptoCurrencyAdapter(ArrayList<CryptoCurrencyCoin> coins) {
        mCoins = coins;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_cryptocurrency_coin,
                                            parent,false);

        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CryptoCurrencyCoin coin = mCoins.get(position);
        holder.bind(coin);
    }

    @Override
    public int getItemCount() {
        return mCoins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView cryptoAbbrImageView;
        private TextView cryptoAbbrNameTextView;
        private TextView cryptoRateTextView;
        private TextView usdRateTextView;
        private ImageView percentChangeImageView;

        public ViewHolder(View itemView) {

            super(itemView);

            cryptoAbbrImageView = (ImageView) itemView.findViewById(R.id.ic_cryptocurrency);
            cryptoAbbrNameTextView = (TextView) itemView.findViewById(R.id.name_abbr_cryptocurrency);
            cryptoRateTextView = (TextView) itemView.findViewById(R.id.rate_cryptocurrency);
            usdRateTextView = (TextView) itemView.findViewById(R.id.rate_usd);
            percentChangeImageView = (ImageView) itemView.findViewById(R.id.arrow);
        }

        public void bind(CryptoCurrencyCoin coin) {

            String symbol = coin.getSymbol();
            symbol = (symbol.length() < 3) ? symbol : symbol.substring(0, 3);

            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getRandomColor();

            TextDrawable.IBuilder builder = TextDrawable.builder()
                                                        .beginConfig()
                                                        .fontSize(48)
                                                        .bold()
                                                        .endConfig()
                                                        .round();

            TextDrawable drawable = builder.build(symbol, color);
            cryptoAbbrImageView.setImageDrawable(drawable);

            cryptoRateTextView.setText(String.format("1 %s", coin.getSymbol()));
            cryptoAbbrNameTextView.setText(String.format("%s - %s", coin.getSymbol(),
                                                            coin.getName()));
            usdRateTextView.setText(String.format("$%.2f",
                    Double.parseDouble(coin.getPriceUsd())));

            if (coin.hasNegativePercentChange1h()) {
                percentChangeImageView.setImageResource(R.drawable.percent_drop_36dp);
            } else {
                percentChangeImageView.setImageResource(R.drawable.percent_rise_36dp);
            }
        }
    }
}
