package com.example.stutern_cryptocurrencyapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.example.stutern_cryptocurrencyapplication.models.CryptoCurrencyCoin;

public class CoinDataDialogFragment extends DialogFragment {

    public static CoinDataDialogFragment newInstance(Parcelable coin) {
        CoinDataDialogFragment fragment = new CoinDataDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable("coin", coin);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        CryptoCurrencyCoin coin = (CryptoCurrencyCoin) getArguments().getParcelable("coin");

        View coinDataView = inflater.inflate(R.layout.dialog_coin_data, null);
        TextView nameSymbolTextView = (TextView) coinDataView.findViewById(R.id.cryptocurrency_name);
        TextView usdPriceTextView = (TextView) coinDataView.findViewById(R.id.price_usd_value);
        TextView marketCapTextView = (TextView) coinDataView.findViewById(R.id.market_cap_value);
        TextView volumePerDayTextView = (TextView) coinDataView.findViewById(R.id.vol_24h_value);
        TextView availableSupplyTextView = (TextView)
                coinDataView.findViewById(R.id.available_supply_value);
        TextView totalSupplyTextView = (TextView)
                coinDataView.findViewById(R.id.total_supply_value);
        TextView percentageChangeTextView = (TextView)
                coinDataView.findViewById(R.id.percentage_change_value);

        nameSymbolTextView.setText(String.format("%s (%s)", coin.getName(), coin.getSymbol()));
        usdPriceTextView.setText(String.format("$%.2f", Double.parseDouble(coin.getPriceUsd())));
        marketCapTextView.setText(coin.getMarketCapUsd());
        volumePerDayTextView.setText(coin.get24hVolumeUsd());
        availableSupplyTextView.setText(coin.getAvailableSupply());
        totalSupplyTextView.setText(coin.getTotalSupply());
        percentageChangeTextView.setText(coin.getPercentChange24h());
        if (coin.hasNegative24HourPercentageChange()) {
            Drawable drawable = ContextCompat.getDrawable(getActivity(),
                                                            R.drawable.ic_arrow_drop_down_red_24dp);
            percentageChangeTextView.setCompoundDrawables(drawable,
                                                        null, null, null);
        } else {
            Drawable drawable = ContextCompat.getDrawable(getActivity(),
                    R.drawable.ic_arrow_drop_up_green_24dp);
            percentageChangeTextView.setCompoundDrawables(drawable,
                                                        null, null, null);
        }

        builder.setView(coinDataView)
                .setPositiveButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        return builder.create();
    }
}
