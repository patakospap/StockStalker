package com.example.panagiotis.testtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static com.example.panagiotis.testtest.MainActivity.stocks;

public class MyStockAdapter extends ArrayAdapter {
    private static final String TAG = "MyStockAdapter";

    private final LayoutInflater inflater;
    private final int layoutResource;
    private final List<MyStock> myPortfstocks;

    public MyStockAdapter(Context context,
                        int resource,
                        List<MyStock> objects) {
        super(context, resource, objects);

        inflater = LayoutInflater.from(context);
        layoutResource = resource;
        myPortfstocks = objects;
    }

    @Override
    public int getCount() {
        return myPortfstocks.size();
    }

    @NonNull

    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View view = inflater.inflate(layoutResource,
                parent,
                false);

        TextView symbol = view.findViewById(R.id.symbolPortfTxt);
        TextView price = view.findViewById(R.id.pricePortfTxt);
        TextView numOfStocks = view.findViewById(R.id.numOfStocksPortfTxt);
        TextView gainLoss = view.findViewById(R.id.gainOrLosstxt);

        MyStock mystock = myPortfstocks.get(position);
        float GainOrLoss = mystock.getGainOrLoss(stocks);


        symbol.setText(mystock.getSymbol());
        price.setText(mystock.getPrice());
        numOfStocks.setText(mystock.getNumOfStocks());
        gainLoss.setText(String.valueOf(GainOrLoss));

        return view;
    }
}
