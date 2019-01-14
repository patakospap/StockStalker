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
import java.util.Locale;

public class StockAdapter extends ArrayAdapter {
    private static final String TAG = "PostAdapter";

    private final LayoutInflater inflater;
    private final int layoutResource;
    private final List<Stock> stocks;

    public StockAdapter(Context context,
                        int resource,
                        List<Stock> objects) {
        super(context, resource, objects);

        inflater = LayoutInflater.from(context);
        layoutResource = resource;
        stocks = objects;
    }

    @Override
    public int getCount() {
        return stocks.size();
    }

    @NonNull

    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View view = inflater.inflate(layoutResource,
                parent,
                false);

        TextView symbol = view.findViewById(R.id.symbolTxtUpdate);
        TextView price = view.findViewById(R.id.pricetxt);
        TextView change = view.findViewById(R.id.changetxt);

        Stock stock = stocks.get(position);
        symbol.setText(stock.getSymbol());

        float pricef = Float.parseFloat(stock.getPrice());
        price.setText(String.format(Locale.US,"%.2f",pricef));

        change.setText(stock.getChange());

        return view;
    }

}
